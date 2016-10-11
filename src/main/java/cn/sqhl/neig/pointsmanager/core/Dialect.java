package cn.sqhl.neig.pointsmanager.core;

public interface Dialect {
	/**
	 * 生成得到总记录数SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 总记录数的sql
	 */
	public String buildCountSql(String sql);

	/**
	 * 生成分页sql
	 * 
	 * @param sql
	 *            查询sql
	 * @param start
	 * @param pageSize
	 * @return 分页sql
	 */
	public String buildPageSql(String sql, PageCond page);

	/**
	 * 构建一个 恒没有结果 的sql</br> 应用场景：当查询返回的 count 为0
	 * ，也就是没有结果时，就没有必要用之前条件的sql再查询一次了，而是使用此处返回的简单查询sql;</br> ps:
	 * 这其实是一个折中的方法，更好的做法是 停止当前查询，直接返回空结果
	 * 
	 * @return 没有查询结果的sql
	 */
	public String buildNotResultSql();
}
