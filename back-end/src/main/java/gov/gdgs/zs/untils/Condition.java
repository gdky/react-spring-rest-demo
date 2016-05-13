package gov.gdgs.zs.untils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 查询条件拼接工具，主要用于生成where...and..此段条件查询语句
 * 用法: 首先创建一个Condition对象，new Condition();
 * 然后用add(字段名，查询类型，传参值)加入查询条件,
 * 如add("name","EQUAL",map.get("name"))，会产生一条 'name = ?'的sql语句，map.get("name")会被放进变量参数的List中
 * 可用getSql取出拼接好的sql语句;
 * 可用getParams取出变量数组，类型为ArrayList
 * 可用add("string")拼接普通sql语句
 * @author ming
 *
 */

public class Condition {

	/**
	 * 相等
	 */
	public static final String EQUAL = "EQUAL"; // 相等
	/**
	 * 不相等
	 */
	public static final String NOT_EQUAL = "NOT_EQUAL"; // 不相等
	/**
	 * 小于
	 */
	public static final String LESS_THEN = "LESS_THEN"; // 小于
	/**
	 * 小于等于
	 */
	public static final String LESS_EQUAL = "LESS_EQUAL"; // 小于等于
	/**
	 *  大于等于
	 */
	public static final String GREATER_EQUAL = "GREATER_EQUAL"; // 大于等于
	/**
	 * 大于
	 */
	public static final String GREATER_THEN = "GREATER_THEN"; // 大于
	/**
	 * 模糊匹配 %xxx%
	 */
	public static final String FUZZY = "FUZZY"; // 模糊匹配 %xxx%
	/**
	 * 左模糊 %xxx
	 */
	public static final String FUZZY_LEFT = "FUZZY_LEFT"; // 左模糊 %xxx

	public static final String FUZZY_RIGHT = "FUZZY_RIGHT"; // 右模糊 xxx%

	public static final String NOT_EMPTY = "NOT_EMPTY"; // 不为空值的情况

	public static final String EMPTY = "EMPTY"; // 空值的情况

	public static final String IN = "IN"; // 在范围内

	public static final String NOT_IN = "NOT_IN"; // 不在范围内

	private final StringBuffer sb;

	private final ArrayList<Object> params;

	public Condition() {
		this.sb = new StringBuffer(" where 1=1 ");
		this.params = new ArrayList<Object>();
	}

	//取出当前sql语句
	public String getSql() {
		return this.sb.toString();
	}

	//取出用于传参的变量组
	public ArrayList<Object> getParams() {
		return (ArrayList<Object>) this.params.clone();
	}

	//拼接传入的sql语句
	public Condition add(String str) {
		this.sb.append(str);
		return this;
	}

	//拼接传入的sql语句
	public Condition add(StringBuffer str) {
		this.sb.append(str);
		return this;
	}

	//拼接查询条件
	public Condition add(String colName, String type, Object param) {
		if (param != null) {
			if (EQUAL.equals(type)) {
				sb.append(" AND ").append(colName).append(" = ? ");
				this.params.add(param);
			} else if (NOT_EQUAL.equals(type)) {
				sb.append(" AND ").append(colName).append(" <> ? ");
				this.params.add(param);
			} else if (LESS_THEN.equals(type)) {
				sb.append(" AND ").append(colName).append(" < ? ");
				this.params.add(param);
			} else if (LESS_EQUAL.equals(type)) {
				sb.append(" AND ").append(colName).append(" <= ? ");
				this.params.add(param);
			} else if (GREATER_EQUAL.equals(type)) {
				sb.append(" AND ").append(colName).append(" >= ? ");
				this.params.add(param);
			} else if (GREATER_THEN.equals(type)) {
				sb.append(" AND ").append(colName).append(" > ? ");
				this.params.add(param);
			} else if (FUZZY.equals(type)) {
				sb.append(" AND ").append(colName).append(" like  ? ");
				this.params.add("%" + param + "%");
			} else if (FUZZY_LEFT.equals(type)) {
				sb.append(" AND ").append(colName).append(" like  ? ");
				this.params.add("%"+ param );
			}
		}
		return this;
	}

	/**
	 * 产生基于当前condition对象的计数语句
	 * @param colForCount 用于统计的列名
	 * @param table 表名
	 *
	 */
	public String getCountSql(String colForCount, String table) {
		StringBuffer sql = new StringBuffer();
		colForCount = "count(" + colForCount + ")";
		sql.append(" select ").append(colForCount).append(" from ")
				.append(table);
		sql.append(this.sb);
		return sql.toString();
	}

	/**
	 * 产生基于当前condition的选择列语句
	 * @param table 查询表
	 * @param colNames 列名，可以多个
	 *
	 */
	public String getSelectSql(String table, String... colNames) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(StringUtils.join(colNames, ","));
		sql.append(" from ").append(table).append(this.sb);
		return sql.toString();
	}
}
