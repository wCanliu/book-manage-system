package com.gin.dao.impl;

import com.gin.dao.*;
import com.gin.entity.*;
import com.gin.util.DBUtil;
import com.gin.util.StringUtil;
import com.jntoo.db.DB;
import java.sql.*;
import java.util.*;

public class GuihuanDaoImpl implements GuihuanDao {

    /**
     * 查询所有行数据
     *
     * @return
     */

    public List<Guihuan> selectAll() {
        return DB.select("SELECT * FROM guihuan", Guihuan.class);
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
        Map<String, String> map = DB.find("SELECT COUNT(*) as count FROM guihuan WHERE " + (where));
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

    public List<Guihuan> selectPages(int pagesize, int page, String where, String orderby) {
        int offset = (page - 1) * pagesize; // 分页位置
        String sql = "SELECT * FROM guihuan " + "WHERE " + where + " ORDER BY " + orderby + " " + "LIMIT " + offset + "," + pagesize;

        List<Guihuan> list = DB.select(sql, Guihuan.class);
        return list;
    }

    /**
     * 根据id 查询一行
     *
     * @param id
     * @return
     */

    public Guihuan find(int id) {
        return DB.find("SELECT * FROM guihuan WHERE id=?", Guihuan.class, id);
    }

    /**
     * find别名
     *
     * @param id
     * @return
     */

    public Guihuan selectOne(int id) {
        return find(id);
    }

    /**
     * 根据条件查询一行数据
     *
     * @param where
     * @return
     */

    public Guihuan findWhere(String where) {
        if (StringUtil.isNullOrEmpty(where)) {
            where = "1=1";
        }
        return DB.find("SELECT * FROM guihuan WHERE " + where, Guihuan.class);
    }

    /**
     * findWhere别名
     *
     * @param where
     * @return
     */

    public Guihuan selectOneWhere(String where) {
        return findWhere(where);
    }

    /**
     * 根据id 更新数据
     *
     * @param guihuan     * @return
     */

    public int update(Guihuan guihuan) {
        if (guihuan.getId() == null) {
            return -1;
        }

        String sql = "UPDATE guihuan SET ";
        List<String> tmpList = new ArrayList();
        List<Object> values = new ArrayList();

        // 将数据不为null 得写入数据库
        if (guihuan.getJieyueid() != null) {
            tmpList.add("jieyueid=?");
            values.add(guihuan.getJieyueid());
        }
        if (guihuan.getShujiid() != null) {
            tmpList.add("shujiid=?");
            values.add(guihuan.getShujiid());
        }
        if (guihuan.getMingcheng() != null) {
            tmpList.add("mingcheng=?");
            values.add(guihuan.getMingcheng());
        }
        if (guihuan.getFenlei() != null) {
            tmpList.add("fenlei=?");
            values.add(guihuan.getFenlei());
        }
        if (guihuan.getZuozhe() != null) {
            tmpList.add("zuozhe=?");
            values.add(guihuan.getZuozhe());
        }
        if (guihuan.getYonghu() != null) {
            tmpList.add("yonghu=?");
            values.add(guihuan.getYonghu());
        }
        if (guihuan.getAddtime() != null) {
            tmpList.add("addtime=?");
            values.add(guihuan.getAddtime());
        }

        sql += StringUtil.join(" , ", tmpList); // 将这些列表写入到sql 语句中去
        sql += " WHERE id=? ";
        values.add(guihuan.getId());

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
     * @param guihuan     * @return
     */

    public long insert(Guihuan guihuan) {
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = -1;

        try {
            String sql = "INSERT INTO guihuan(jieyueid,shujiid,mingcheng,fenlei,zuozhe,yonghu,addtime) VALUES (?,?,?,?,?,?,?)";
            System.out.printf(sql);
            statement = conn.prepareStatement(sql, 1);
            statement.setObject(1, guihuan.getJieyueid());
            statement.setObject(2, guihuan.getShujiid());
            statement.setObject(3, guihuan.getMingcheng());
            statement.setObject(4, guihuan.getFenlei());
            statement.setObject(5, guihuan.getZuozhe());
            statement.setObject(6, guihuan.getYonghu());
            statement.setObject(7, guihuan.getAddtime());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            result = resultSet.getInt(1);
            guihuan.setId(result);
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
        return DB.execute("DELETE FROM guihuan WHERE id in(" + StringUtil.join(",", ids) + ")");
    }

    /**
     * 根据id 删除行
     *
     * @param id
     * @return
     */

    public int delete(Object id) {
        return DB.execute("DELETE FROM guihuan WHERE id='" + id + "'");
    }

    /**
     * 根据条件删除行
     *
     * @param where
     * @return
     */

    public int deleteWhere(String where) {
        return DB.execute("DELETE FROM guihuan WHERE " + where);
    }
}
