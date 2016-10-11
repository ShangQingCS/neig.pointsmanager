package cn.sqhl.neig.pointsmanager.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLHelper {
	private static Logger logger = LogManager.getLogger(SQLHelper.class);

	/**
	 * 获取总条数
	 * 
	 * @param sql
	 * @param mappedStatement
	 * @param parameterObject
	 * @param boundSql
	 * @param dialect
	 * @return
	 * @throws SQLException 
	 */
	public static int getCount(final String sql,
			final MappedStatement mappedStatement,
			final Object parameterObject, final BoundSql boundSql,
			Dialect dialect) throws SQLException {
		final String count_sql = dialect.buildCountSql(sql);
		logger.debug("Total count SQL [{" + count_sql + "}] ");
		logger.debug("Total count Parameters: {" + parameterObject + "} ");

		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			connection = mappedStatement.getConfiguration().getEnvironment()
					.getDataSource().getConnection();
			countStmt = connection.prepareStatement(count_sql);
			// Page SQL和Count SQL的参数是一样的，在绑定参数时可以使用一样的boundSql
			DefaultParameterHandler handler = new DefaultParameterHandler(
					mappedStatement, parameterObject, boundSql);
			handler.setParameters(countStmt);

			rs = countStmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			logger.debug("Total count: {" + count + "}");
			return count;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} finally {
				try {
					if (countStmt != null) {
						countStmt.close();
					}
				} finally {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				}
			}
		}
	}
}
