package cn.sqhl.neig.pointsmanager.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.sqhl.neig.pointsmanager.utils.PageCond;


public class OracleDialect implements Dialect {

	private static Logger log = LogManager.getLogger(OracleDialect.class);
	
	/**
	 * 生成得到总记录数SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 总记录数的sql
	 */
	public String buildCountSql(String sql) {
		sql = replaceFormatSqlOrderBy(sql);
		return "select count(*) from (" + sql + ") tmp_count";
	}

	/**
	 * 生成分页sql
	 * 
	 * @param sql
	 *            查询sql
	 * @param start
	 * @param pageSize
	 * @return 分页sql
	 */
	public String buildPageSql(String sql, PageCond page) {
		// 检查和填充page对象字段
		page.checkAndFillPageCond();

		// 去除SQL手尾的空格
		sql = sql.trim();

		int begin = page.getBegin();
		int end = (page.getBegin()+page.getLength());
		sql = "select * from(SELECT row_.*,rownum rn FROM( " + sql +" ) row_ WHERE rownum <= " + end +") where rn > " + begin;
		log.debug("构建出的分页sql：" + sql);
		
		return sql;
	}

	/**
	 * 构建一个 恒没有结果 的sql</br> 应用场景：当查询返回的 count 为0
	 * ，也就是没有结果时，就没有必要用之前条件的sql再查询一次了，而是使用此处返回的简单查询sdl;</br> ps:
	 * 这其实是一个折中的方法，更好的做法是 停止当前查询，直接返回空结果
	 * 
	 * @return 没有查询结果的sql
	 */
	public String buildNotResultSql() {
		return "select null from dual where 1!=1";
	}
	
	/**
	 * 连续多空格替换为单个空格，去除不影响求和的order by (不会去除 oracle如 分析函数中的 order by );
	 * @param sql
	 * @return
	 */
	public static String replaceFormatSqlOrderBy(String sql) {
		sql = sql.replaceAll("(\\s)+", " ");
		int index = sql.toLowerCase().lastIndexOf("order by");
		if (index > sql.toLowerCase().lastIndexOf(")")) {
			String sql1 = sql.substring(0, index);
			String sql2 = sql.substring(index);
			sql2 = sql2
					.replaceAll(
							"[oO][rR][dD][eE][rR] [bB][yY] [\u4e00-\u9fa5a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[\u4e00-\u9fa5a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*",
							"");
			return sql1 + sql2;
		}
		return sql;
	}
}
