package com.gin.dao.impl;

import com.gin.dao.*;
import com.gin.entity.*;
import com.gin.util.DBUtil;
import com.gin.util.StringUtil;
import com.jntoo.db.DB;
import java.sql.*;
import java.util.*;

public class ShujiDaoImpl implements ShujiDao {

    /**
     * 查询所有行数据
     *
     * @return
     */

    public List<Shuji> selectAll() {
        return DB.select("SELECT * FROM shuji", Shuji.class);
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
        Map<String, String> map = DB.find("SELECT COUNT(*) as count FROM shuji WHERE " + (where));
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

    public List<Shuji> selectPages(int pagesize, int page, String where, String orderby) {
        int offset = (page - 1) * pagesize; // 分页位置
        String sql = "SELECT * FROM shuji " + "WHERE " + where + " ORDER BY " + orderby + " " + "LIMIT " + offset + "," + pagesize;

        List<Shuji> list = DB.select(sql, Shuji.class);
        return list;
    }

    /**
     * 根据id 查询一行
     *
     * @param id
     * @return
     */

    public Shuji find(int id) {
        return DB.find("SELECT * FROM shuji WHERE id=?", Shuji.class, id);
    }

    /**
     * find别名
     *
     * @param id
     * @return
     */

    public Shuji selectOne(int id) {
        return find(id);
    }

    /**
     * 根据条件查询一行数据
     *
     * @param where
     * @return
     */

    public Shuji findWhere(String where) {
        if (StringUtil.isNullOrEmpty(where)) {
            where = "1=1";
        }
        return DB.find("SELECT * FROM shuji WHERE " + where, Shuji.class);
    }

    /**
     * findWhere别名
     *
     * @param where
     * @return
     */

    public Shuji selectOneWhere(String where) {
        return findWhere(where);
    }

    /**
     * 根据id 更新数据
     *
     * @param shuji     * @return
     */

    public int update(Shuji shuji) {
        if (shuji.getId() == null) {
            return -1;
        }

        String sql = "UPDATE shuji SET ";
        List<String> tmpList = new ArrayList();
        List<Object> values = new ArrayList();

        // 将数据不为null 得写入数据库
        if (shuji.getMingcheng() != null) {
            tmpList.add("mingcheng=?");
            values.add(shuji.getMingcheng());
        }
        if (shuji.getFenlei() != null) {
            tmpList.add("fenlei=?");
            values.add(shuji.getFenlei());
        }
        if (shuji.getZuozhe() != null) {
            tmpList.add("zuozhe=?");
            values.add(shuji.getZuozhe());
        }
        if (shuji.getChubanshe() != null) {
            tmpList.add("chubanshe=?");
            values.add(shuji.getChubanshe());
        }
        if (shuji.getIsbn() != null) {
            tmpList.add("isbn=?");
            values.add(shuji.getIsbn());
        }
        if (shuji.getShuliang() != null) {
            tmpList.add("shuliang=?");
            values.add(shuji.getShuliang());
        }
        if (shuji.getTupian() != null) {
            tmpList.add("tupian=?");
            values.add(shuji.getTupian());
        }
        if (shuji.getJianjie() != null) {
            tmpList.add("jianjie=?");
            values.add(shuji.getJianjie());
        }

        sql += StringUtil.join(" , ", tmpList); // 将这些列表写入到sql 语句中去
        sql += " WHERE id=? ";
        values.add(shuji.getId());

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
     * @param shuji     * @return
     */

    public long insert(Shuji shuji) {
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = -1;

        try {
            String sql = "INSERT INTO shuji(mingcheng,fenlei,zuozhe,chubanshe,isbn,shuliang,tupian,jianjie) VALUES (?,?,?,?,?,?,?,?)";
            System.out.printf(sql);
            statement = conn.prepareStatement(sql, 1);
            statement.setObject(1, shuji.getMingcheng());
            statement.setObject(2, shuji.getFenlei());
            statement.setObject(3, shuji.getZuozhe());
            statement.setObject(4, shuji.getChubanshe());
            statement.setObject(5, shuji.getIsbn());
            statement.setObject(6, shuji.getShuliang());
            statement.setObject(7, shuji.getTupian());
            statement.setObject(8, shuji.getJianjie());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            result = resultSet.getInt(1);
            shuji.setId(result);
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
        return DB.execute("DELETE FROM shuji WHERE id in(" + StringUtil.join(",", ids) + ")");
    }

    /**
     * 根据id 删除行
     *
     * @param id
     * @return
     */

    public int delete(Object id) {
        return DB.execute("DELETE FROM shuji WHERE id='" + id + "'");
    }

    /**
     * 根据条件删除行
     *
     * @param where
     * @return
     */

    public int deleteWhere(String where) {
        return DB.execute("DELETE FROM shuji WHERE " + where);
    }
}
