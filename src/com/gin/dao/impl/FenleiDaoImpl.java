package com.gin.dao.impl;

import com.gin.dao.*;
import com.gin.entity.*;
import com.gin.util.DBUtil;
import com.gin.util.StringUtil;
import com.jntoo.db.DB;
import java.sql.*;
import java.util.*;

public class FenleiDaoImpl implements FenleiDao {

    /**
     * 查询所有行数据
     *
     * @return
     */

    public List<Fenlei> selectAll() {
        return DB.select("SELECT * FROM fenlei", Fenlei.class);
    }

    /**
     * 根据条件查询行数
     *
     * @param where
     * @return
     */

    public long count(String where) {
        if (StringUtil.isNullOrEmpty(where)) {
            where = "1=1";
        }
        Map<String, String> map = DB.find("SELECT COUNT(*) as count FROM fenlei WHERE " + (where));
        if (map != null) {
            return Long.valueOf(map.get("count")).longValue();
        }
        return 0;
    }

    /**
     * 根据条件查询列表并分页
     *
     * @param pagesize
     * @param page
     * @param where
     * @param orderby
     * @return
     */

    public List<Fenlei> selectPages(int pagesize, int page, String where, String orderby) {
        int offset = (page - 1) * pagesize; // 分页位置
        String sql = "SELECT * FROM fenlei " + "WHERE " + where + " ORDER BY " + orderby + " " + "LIMIT " + offset + "," + pagesize;

        List<Fenlei> list = DB.select(sql, Fenlei.class);
        return list;
    }

    /**
     * 根据id 查询一行
     *
     * @param id
     * @return
     */

    public Fenlei find(int id) {
        return DB.find("SELECT * FROM fenlei WHERE id=?", Fenlei.class, id);
    }

    /**
     * find别名
     *
     * @param id
     * @return
     */

    public Fenlei selectOne(int id) {
        return find(id);
    }

    /**
     * 根据条件查询一行数据
     *
     * @param where
     * @return
     */

    public Fenlei findWhere(String where) {
        if (StringUtil.isNullOrEmpty(where)) {
            where = "1=1";
        }
        return DB.find("SELECT * FROM fenlei WHERE " + where, Fenlei.class);
    }

    /**
     * findWhere别名
     *
     * @param where
     * @return
     */

    public Fenlei selectOneWhere(String where) {
        return findWhere(where);
    }

    /**
     * 根据id 更新数据
     *
     * @param fenlei     * @return
     */

    public int update(Fenlei fenlei) {
        if (fenlei.getId() == null) {
            return -1;
        }

        String sql = "UPDATE fenlei SET ";
        List<String> tmpList = new ArrayList();
        List<Object> values = new ArrayList();

        // 将数据不为null 得写入数据库
        if (fenlei.getFenleiming() != null) {
            tmpList.add("fenleiming=?");
            values.add(fenlei.getFenleiming());
        }

        sql += StringUtil.join(" , ", tmpList); // 将这些列表写入到sql 语句中去
        sql += " WHERE id=? ";
        values.add(fenlei.getId());

        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        int result = -1;
        try {
            System.out.printf(sql);
            statement = conn.prepareStatement(sql);
            int i = 1;
            for (Object v : values) {
                statement.setObject(i, v);
                i++;
            }
            result = statement.executeUpdate();
            DB.log(statement.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(statement, null);
        }
        return result;
    }

    /**
     * 根据实体类插入数据
     *
     * @param fenlei     * @return
     */

    public long insert(Fenlei fenlei) {
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = -1;

        try {
            String sql = "INSERT INTO fenlei(fenleiming) VALUES (?)";
            System.out.printf(sql);
            statement = conn.prepareStatement(sql, 1);
            statement.setObject(1, fenlei.getFenleiming());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            result = resultSet.getInt(1);
            fenlei.setId(result);
            DB.log(statement.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.release(statement, resultSet);
        }
        return result;
    }

    /**
     * 根据列表id 删除数据行
     *
     * @param ids
     * @return
     */

    public int delete(List ids) {
        return DB.execute("DELETE FROM fenlei WHERE id in(" + StringUtil.join(",", ids) + ")");
    }

    /**
     * 根据id 删除行
     *
     * @param id
     * @return
     */

    public int delete(Object id) {
        return DB.execute("DELETE FROM fenlei WHERE id='" + id + "'");
    }

    /**
     * 根据条件删除行
     *
     * @param where
     * @return
     */

    public int deleteWhere(String where) {
        return DB.execute("DELETE FROM fenlei WHERE " + where);
    }
}
