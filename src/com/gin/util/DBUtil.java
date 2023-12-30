package com.gin.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

    // 数据库名称
    private static final String database = "tsglxt";
    // 数据库账号
    private static final String username = "root";
    // 数据库密码
    private static final String pwd = "wyf030212";
    // 是否为 mysql8.0及以上、如果是则把 false 改成 true
    private static final boolean isMysql8 = true; // 是否为mysql8

    public static Connection conn = null;

    /**
     * 数据库链接类
     * @return
     */
    public static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                String connstr = getConnectString();
                conn = DriverManager.getConnection(connstr, username, pwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String getConnectString() {
        try {
            String connstr;
            if (!isMysql8) {
                Class.forName("com.mysql.jdbc.Driver");
                connstr = String.format("jdbc:mysql://localhost:3306/%s?useUnicode=true&characterEncoding=UTF-8&useOldAliasMetadataBehavior=true", database);
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connstr =
                    String.format(
                        "jdbc:mysql://localhost:3306/%s?useUnicode=true&characterEncoding=UTF-8&useSSL=FALSE&serverTimezone=UTC&useOldAliasMetadataBehavior=true",
                        database
                    );
            }
            return connstr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 执行sql 语句
     * @param sql
     * @return
     */
    public static long execute(String sql) {
        long autoInsertId = -1;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = getConn().createStatement();
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                autoInsertId = rs.getLong(1);
            }
            System.out.println(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            log(e, sql);
        } finally {
            release(st, rs);
        }
        return autoInsertId;
    }

    /**
     * 根据sql 语句获取一行数据
     * @param sql
     * @return
     */
    public static Map find(String sql) {
        HashMap map = new HashMap();

        //List<HashMap> list = new ArrayList();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = getConn().createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                //HashMap map = new HashMap();
                int i = rsmd.getColumnCount();
                for (int j = 1; j <= i; j++) {
                    if (!rsmd.getColumnName(j).equals("ID")) {
                        String str = rs.getString(j) == null ? "" : rs.getString(j);
                        if (str.equals("null")) str = "";
                        map.put(rsmd.getColumnName(j), str);
                    } else map.put("id", rs.getString(j));
                }
                break;
            }
            System.out.println(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            log(e, sql);
        } finally {
            release(st, rs);
        }
        return map;
    }

    /**
     * 根据SQL语句获取数据行
     * @param sql
     * @return
     */
    public static List<Map> select(String sql) {
        List<Map> list = new ArrayList();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = getConn().createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                HashMap map = new HashMap();
                int i = rsmd.getColumnCount();
                for (int j = 1; j <= i; j++) {
                    if (!rsmd.getColumnName(j).equals("ID")) {
                        String str = rs.getString(j) == null ? "" : rs.getString(j);
                        if (str.equals("null")) str = "";
                        map.put(rsmd.getColumnName(j), str);
                    } else map.put("id", rs.getString(j));
                }
                list.add(map);
            }
            System.out.println(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log(e, sql);
            //e.printStackTrace();
        } finally {
            release(st, rs);
        }
        return list;
    }

    public static void release(Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void log(SQLException e, String sql) {
        int code = e.getErrorCode();
        String message = e.getMessage();
        String errorMessage = String.format("SQL execute Error sql: \n%s\nMessage:%s", sql, message);
        System.err.println(errorMessage);
    }
}
