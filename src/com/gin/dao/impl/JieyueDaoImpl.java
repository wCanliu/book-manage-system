package com.gin.dao.impl;

import com.gin.dao.*;
import com.gin.entity.*;
import com.gin.util.DBUtil;
import com.gin.util.StringUtil;
import com.jntoo.db.DB;
import java.sql.*;
import java.util.*;

public class JieyueDaoImpl implements JieyueDao {

    /**
     * 查询所有行数据
     *
     * @return
     */

    public List<Jieyue> selectAll() {
        return DB.select("SELECT * FROM jieyue", Jieyue.class);
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
        Map<String, String> map = DB.find("SELECT COUNT(*) as count FROM jieyue WHERE " + (where));
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

    public List<Jieyue> selectPages(int pagesize, int page, String where, String orderby) {
        int offset = (page - 1) * pagesize; // 分页位置
        String sql = "SELECT * FROM jieyue " + "WHERE " + where + " ORDER BY " + orderby + " " + "LIMIT " + offset + "," + pagesize;

        List<Jieyue> list = DB.select(sql, Jieyue.class);
        return list;
    }

    /**
     * 根据id 查询一行
     *
     * @param id
     * @return
     */

    public Jieyue find(int id) {
        return DB.find("SELECT * FROM jieyue WHERE id=?", Jieyue.class, id);
    }

    /**
     * find别名
     *
     * @param id
     * @return
     */

    public Jieyue selectOne(int id) {
        return find(id);
    }

    /**
     * 根据条件查询一行数据
     *
     * @param where
     * @return
     */

    public Jieyue findWhere(String where) {
        if (StringUtil.isNullOrEmpty(where)) {
            where = "1=1";
        }
        return DB.find("SELECT * FROM jieyue WHERE " + where, Jieyue.class);
    }

    /**
     * findWhere别名
     *
     * @param where
     * @return
     */

    public Jieyue selectOneWhere(String where) {
        return findWhere(where);
    }

    /**
     * 根据id 更新数据
     *
     * @param jieyue     * @return
     */

    public int update(Jieyue jieyue) {
        if (jieyue.getId() == null) {
            return -1;
        }

        String sql = "UPDATE jieyue SET ";
        List<String> tmpList = new ArrayList();
        List<Object> values = new ArrayList();

        // 将数据不为null 得写入数据库
        if (jieyue.getShujiid() != null) {
            tmpList.add("shujiid=?");
            values.add(jieyue.getShujiid());
        }
        if (jieyue.getMingcheng() != null) {
            tmpList.add("mingcheng=?");
            values.add(jieyue.getMingcheng());
        }
        if (jieyue.getFenlei() != null) {
            tmpList.add("fenlei=?");
            values.add(jieyue.getFenlei());
        }
        if (jieyue.getZuozhe() != null) {
            tmpList.add("zuozhe=?");
            values.add(jieyue.getZuozhe());
        }
        if (jieyue.getYonghu() != null) {
            tmpList.add("yonghu=?");
            values.add(jieyue.getYonghu());
        }
        if (jieyue.getKaishi() != null) {
            tmpList.add("kaishi=?");
            values.add(jieyue.getKaishi());
        }
        if (jieyue.getJieshu() != null) {
            tmpList.add("jieshu=?");
            values.add(jieyue.getJieshu());
        }
        if (jieyue.getShizhang() != null) {
            tmpList.add("shizhang=?");
            values.add(jieyue.getShizhang());
        }
        if (jieyue.getZhuangtai() != null) {
            tmpList.add("zhuangtai=?");
            values.add(jieyue.getZhuangtai());
        }

        sql += StringUtil.join(" , ", tmpList); // 将这些列表写入到sql 语句中去
        sql += " WHERE id=? ";
        values.add(jieyue.getId());

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
     * @param jieyue     * @return
     */

    public long insert(Jieyue jieyue) {
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = -1;

        try {
            String sql = "INSERT INTO jieyue(shujiid,mingcheng,fenlei,zuozhe,yonghu,kaishi,jieshu,shizhang,zhuangtai) VALUES (?,?,?,?,?,?,?,?,?)";
            System.out.printf(sql);
            statement = conn.prepareStatement(sql, 1);
            statement.setObject(1, jieyue.getShujiid());
            statement.setObject(2, jieyue.getMingcheng());
            statement.setObject(3, jieyue.getFenlei());
            statement.setObject(4, jieyue.getZuozhe());
            statement.setObject(5, jieyue.getYonghu());
            statement.setObject(6, jieyue.getKaishi());
            statement.setObject(7, jieyue.getJieshu());
            statement.setObject(8, jieyue.getShizhang());
            statement.setObject(9, jieyue.getZhuangtai());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            result = resultSet.getInt(1);
            jieyue.setId(result);
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
        return DB.execute("DELETE FROM jieyue WHERE id in(" + StringUtil.join(",", ids) + ")");
    }

    /**
     * 根据id 删除行
     *
     * @param id
     * @return
     */

    public int delete(Object id) {
        return DB.execute("DELETE FROM jieyue WHERE id='" + id + "'");
    }

    /**
     * 根据条件删除行
     *
     * @param where
     * @return
     */

    public int deleteWhere(String where) {
        return DB.execute("DELETE FROM jieyue WHERE " + where);
    }
}
