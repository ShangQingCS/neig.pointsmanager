package cn.sqhl.neig.pointsmanager.core;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.sqhl.neig.pointsmanager.utils.PageCond;

/**
 * @author orh
 *         <p>
 *         分页插件类，功能： 必须使用query 开头的方法才能生效
 *         <ul>
 *         <li>自动求得总行数，不需另外的求和sql，通过参数引用的PageCond对象返回</li>
 *         <li>根据PageCond的条件分页，无需对命名sql写分页实现</li>
 *         <li>TODO 可扩展 方言支持 dialect</li>
 *         <li>TODO 多数据源支持</li>
 *         </ul>
 *         调用方式：
 *         <ul>
 *         <li>使用分页查询是必须带入参数 {@link PageCond} 对象</li>
 *         <li>传入的 {@link PageCond} 对象以 @Param("PAGE")注解</li>
 *         </ul>
 *         </p>
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }) })
public class PaginationInterceptor implements Interceptor {
	private final static Logger log = LogManager
			.getLogger(PaginationInterceptor.class);
	public final static String PAGE_PARAM_KEY = "PAGE";
	static int MAPPED_STATEMENT_INDEX = 0; // 索引为0的参数 为 MappedStatement
	static int PARAMETER_INDEX = 1; // 索引为1的参数为调用方法传入的 参数集合
	static int ROWBOUNDS_INDEX = 2; // 索引为2带参数为 RowBounds
	// static int RESULT_HANDLER_INDEX = 3;

	private Dialect dialect;

	public Object intercept(Invocation invocation) throws Throwable {
		// 获取参数，判断是否有分页对象传入，无：不处理 ; 有 ：处理
		Object[] queryArgs = invocation.getArgs();
		Object parameter = queryArgs[PARAMETER_INDEX];
		PageCond page = null;
		if (parameter instanceof HashMap) {
			HashMap paramMap = (HashMap) parameter;
			if (paramMap != null) {
				if (paramMap.containsKey(PAGE_PARAM_KEY)) {
					page = (PageCond) paramMap.get(PAGE_PARAM_KEY);
				}
			}
		}
		if (page == null) {
			log.debug("page is null, proceed!");
			return invocation.proceed();
		}

		// 分页处理
		log.debug("page is not null,Paging in processing! ");

		Executor executor = (Executor) invocation.getTarget();

		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];

		BoundSql boundSql = ms.getBoundSql(parameter);

		StringBuffer bufferSql = new StringBuffer(boundSql.getSql());

		if (page.isCount()) { // 需要求总页数
			Integer count = 0;
			Cache cache = ms.getCache();
			if (ms.isUseCache() && cache != null) {
				CacheKey cacheKey = executor.createCacheKey(ms, parameter,
						rowBounds, boundSql);
				count = (Integer) cache.getObject(cacheKey);
				if (count == null) {
					count = SQLHelper.getCount(boundSql.getSql(), ms,
							parameter, boundSql, dialect);
					cache.putObject(cacheKey, count);
					log.debug("get count for database");
				} else {
					log.debug("get count for cache");
				}
			} else {
				count = SQLHelper.getCount(boundSql.getSql(), ms, parameter,
						boundSql, dialect);
				log.debug("get count for database");
			}
			page.setTotalRows(count);
		}

		String newSql = null;
		List<ParameterMapping> parameterMapping = boundSql.getParameterMappings();
		if (page.getTotalRows() > 0) {
			// 如果有结果，构建分页sql
			newSql = dialect.buildPageSql(bufferSql.toString(), page);
		} else {
			page.checkAndFillPageCond();	//没有数据时，要设置引用的page 设置标识为最后一页
			// 总条数都没有时，则构建一个查询恒没有结果sql
			newSql = dialect.buildNotResultSql();
			//将参数和参数映射map都设置为null
			parameter = null;
			parameterMapping = null;
			log.info("because the query result is 0, will be execute not result sql;");
		}
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), newSql,
				parameterMapping, parameter);
		MappedStatement newMs = buildNewMappedStatement(ms, newBoundSql);
		queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		return invocation.proceed();

	}

	private MappedStatement buildNewMappedStatement(MappedStatement ms,
			BoundSql newBoundSql) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(),
				new BoundSqlSqlSource(newBoundSql), ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuffer keyProperties = new StringBuffer();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1,
					keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {

	}

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
}