package cn.sqhl.neig.pointsmanager.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.sqhl.neig.pointsmanager.utils.PageCond;

public class MySqlDialect implements Dialect {
	private static Logger log = LogManager.getLogger(MySqlDialect.class);

	public String buildCountSql(String sql) {
		sql = replaceFormatSqlOrderBy(sql);
		return "select count(*) from (" + sql + ") tmp_count";
	}

	/***
	 * tips:使用时查询语句请进行排序
	 */
	public String buildPageSql(String sql, PageCond page) {
		page.checkAndFillPageCond();

		// 去除SQL手尾的空格
		sql = sql.trim();

		int currentpage = page.getCurrentPage();
		int pagesize = page.getLength();
		
		sql = "select * from ("+sql+") as t limit "+(currentpage-1)*pagesize+","+currentpage*pagesize;
		
		//sql = "select * from(SELECT row_.*,rownum rn FROM( " + sql +" ) row_ WHERE rownum <= " + end +") where rn > " + begin;
		log.debug("构建出的分页sql：" + sql);
		
		return sql;
	}

	public String buildNotResultSql() {
		return "select null from dual where 1!=1";
	}
	
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
