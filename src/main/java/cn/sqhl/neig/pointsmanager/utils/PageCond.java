package cn.sqhl.neig.pointsmanager.utils;

import java.io.Serializable;

/**
 * 分页对象
 * 
 * @author orh
 * 
 */
public class PageCond implements Serializable {

	private static final long serialVersionUID = 2063456390062894326L;

	private static int DEFAULT_LENGTH = 10;
	private boolean DEFAULT_IS_COUNT = true;

	/**
	 * 
	 * @param begin
	 *            记录起始号
	 * @param length
	 *            每页显示多少条记录
	 */
	public PageCond(int begin, int length) {
		this.begin = begin;
		this.length = length;
	}

	/**
	 * 获取默认的 PageCond分页实例;</br>
	 * <ul>
	 * <li>默认从第一页显示,也就是begin为0;</li>
	 * <li>默认每页显示 10条记录</li>
	 * </ul>
	 */
	public PageCond() {
		this.begin = 0;
		this.length = DEFAULT_LENGTH;
	}

	@Override
	public String toString() {
		return "PageCond [起始号：" + begin + ", 每页显示：" + length + ", 是否求总行数："
				+ isCount + ", 总行数：" + totalRows + ", 总页数：" + totalPage
				+ ", 当前页号：" + currentPage + ", 是否首页：" + isFirst + ", 是否末页："
				+ isLast + "]";// , 当前页记录数：" + currentPageRows + "]";
	}

	/**
	 * 分页查询条件，记录的起始记录号
	 */
	private int begin;
	/**
	 * 分页查询条件，记录数长度 ; 默认值：10
	 */
	private int length = DEFAULT_LENGTH;
	/**
	 * 分页查询条件，是否统计总行数; 默认值 true</br>
	 * 当isCount为false时，将不统计总行数，只是简单的分页，无总页数、总记录数、首页、尾页等信息
	 */
	private boolean isCount = DEFAULT_IS_COUNT;

	/**
	 * 分页状态信息，总记录数
	 */
	private int totalRows;

	/**
	 * 分页状态信息，总页数
	 */
	private int totalPage;
	/**
	 * 分页状态信息，当前页号
	 */
	private int currentPage;
	/**
	 * 分页状态信息，是否首页
	 */
	private boolean isFirst;
	/**
	 * 分页状态信息，是否尾页
	 */
	private boolean isLast;
	/**
	 * 分页状态信息，当前页记录数
	 */
	private int currentPageRows;

	/**
	 * @return 分页查询条件，记录的起始记录号
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * @param 分页查询条件
	 *            ，记录的起始记录号
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * @return 分页查询条件，记录数长度; 默认值：10
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param 分页查询条件
	 *            ，记录数长度; 默认值：0
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return 分页查询条件，是否统计总行数; 默认值 true
	 */
	public boolean isCount() {
		return isCount;
	}

	/**
	 * @param 分页查询条件
	 *            ，是否统计总行数; 默认值 true
	 */
	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	/**
	 * @return 分页状态信息，总记录数
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param 分页状态信息
	 *            ，总记录数
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return 分页状态信息，总页数
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param 分页状态信息
	 *            ，总页数
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return 分页状态信息，当前页号
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param 分页状态信息
	 *            ，当前页号
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return 分页状态信息，是否首页
	 */
	public boolean isFirst() {
		return isFirst;
	}

	/**
	 * @param 分页状态信息
	 *            ，是否首页
	 */
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	/**
	 * @return 分页状态信息，是否尾页
	 */
	public boolean isLast() {
		return isLast;
	}

	/**
	 * @param 分页状态信息
	 *            ，是否尾页
	 */
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	/**
	 * @return 分页状态信息，当前页记录数
	 */
	public int getCurrentPageRows() {
		return currentPageRows;
	}

	/**
	 * @param 分页状态信息
	 *            ，当前页记录数
	 */
	public void setCurrentPageRows(int currentPageRows) {
		this.currentPageRows = currentPageRows;
	}

	public void checkAndFillPageCond() {
		if (this.begin < 0) { // 记录的起始记录号 则设置值为0
			this.begin = 0;
		}
		if (this.length <= 0) { // 如果每页条数小于或等于0，则设置为 每页查询默认10条数
			this.length = DEFAULT_LENGTH;
		}
		if (this.isCount) {
			if (this.begin >= this.totalRows) {
				this.begin = this.totalRows;
			}
			// 根据总行数、每页数量计算总页数
			this.totalPage = this.totalRows % this.length == 0 ? this.totalRows
					/ this.length : this.totalRows / this.length + 1;
		}
		// 根据起始记录号、每页数量计算 当前页码
		this.currentPage = (this.begin + this.length) / this.length;
		this.isFirst = this.currentPage == 1;
		this.isLast = this.currentPage >= totalPage;
	}

}
