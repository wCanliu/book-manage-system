package com.gin.service.impl;

import com.gin.dao.*;
import com.gin.dao.impl.*;
import com.gin.entity.*;
import com.gin.service.*;
import java.util.List;

public class GuihuanServiceImpl implements GuihuanService {

    private GuihuanDao dao = new GuihuanDaoImpl();

    /**
     * 查询所有行数据
     *
     * @return
     */

    public List<Guihuan> selectAll() {
        return dao.selectAll();
    }

    /**
     * 根据条件查询行数
     *
     * @param where
     * @return
     */

    public long count(String where) {
        return dao.count(where);
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
        return dao.selectPages(pagesize, page, where, orderby);
    }

    /**
     * 根据id 查询一行
     *
     * @param id
     * @return
     */

    public Guihuan find(int id) {
        return dao.find(id);
    }

    /**
     * find别名
     *
     * @param id
     * @return
     */

    public Guihuan selectOne(int id) {
        return dao.selectOne(id);
    }

    /**
     * 根据条件查询一行数据
     *
     * @param where
     * @return
     */

    public Guihuan findWhere(String where) {
        return dao.findWhere(where);
    }

    /**
     * findWhere别名
     *
     * @param where
     * @return
     */

    public Guihuan selectOneWhere(String where) {
        return dao.selectOneWhere(where);
    }

    /**
     * 根据id 更新数据
     *
     * @param guihuan     * @return
     */

    public int update(Guihuan guihuan) {
        return dao.update(guihuan);
    }

    /**
     * 根据实体类插入数据
     *
     * @param guihuan     * @return
     */

    public long insert(Guihuan guihuan) {
        return dao.insert(guihuan);
    }

    /**
     * 根据列表id 删除数据行
     *
     * @param ids
     * @return
     */

    public int delete(List ids) {
        return dao.delete(ids);
    }

    /**
     * 根据id 删除行
     *
     * @param id
     * @return
     */

    public int delete(Object id) {
        return dao.delete(id);
    }

    /**
     * 根据条件删除行
     *
     * @param where
     * @return
     */

    public int deleteWhere(String where) {
        return dao.deleteWhere(where);
    }
}
