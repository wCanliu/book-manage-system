package com.gin.service;

import com.gin.entity.*;
import java.util.List;

public interface YonghuService {
    Yonghu login(String username, String password);
    boolean editPassword(Object id, String newPassword);

    /**
     * 查询所有行数据
     * @return
     */
    List<Yonghu> selectAll();

    /**
     * 根据条件查询行数
     * @param where
     * @return
     */
    long count(String where);

    /**
     * 根据条件查询列表并分页
     * @param pagesize
     * @param page
     * @param where
     * @param orderby
     * @return
     */
    List<Yonghu> selectPages(int pagesize, int page, String where, String orderby);

    /**
     * 根据id 查询一行
     * @param id
     * @return
     */
    Yonghu find(int id);

    /**
     * find别名
     * @param id
     * @return
     */
    Yonghu selectOne(int id);

    /**
     * 根据条件查询一行数据
     * @param where
     * @return
     */
    Yonghu findWhere(String where);

    /**
     * findWhere别名
     * @param where
     * @return
     */
    Yonghu selectOneWhere(String where);

    /**
     * 根据id 更新数据
     * @param yonghu     * @return
     */
    int update(Yonghu yonghu);

    /**
     * 根据实体类插入数据
     * @param yonghu     * @return
     */
    long insert(Yonghu yonghu);

    /**
     * 根据列表id 删除数据行
     * @param ids
     * @return
     */
    int delete(List ids);

    /**
     * 根据id 删除行
     * @param id
     * @return
     */
    int delete(Object id);

    /**
     * 根据条件删除行
     * @param where
     * @return
     */
    int deleteWhere(String where);
}
