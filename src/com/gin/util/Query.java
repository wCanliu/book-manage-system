package com.gin.util;

import com.jntoo.db.DB;
import com.jntoo.db.QueryWrapperBase;
import com.jntoo.db.utils.CollectUtil;
import java.util.List;
import java.util.Map;

public class Query extends QueryWrapperBase<Map, Query> {

    public Query(String name) {
        super(name);
    }

    public static Query make(String name) {
        return new Query(name);
    }

    // 生成分页信息
    public List page(Integer pagesize) {
        PageInfoUtil pageInfoUtil = new PageInfoUtil();
        String urlRule = pageInfoUtil.getRequestUrlPath();
        CollectUtil<Map> collect = new CollectUtil(pageInfoUtil.getPage(), pagesize); // 创建分页对象
        collect = super.page(collect); // 查询列表并分页
        collect.setUrlRule(urlRule);
        new Collect(collect.getTotalRows(), pagesize);

        return collect.getLists();
    }
}
