package com.lance.hp.hp_study.common.api;


import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName CommonPage.java
 * @Description 分页数据封装类
 * @createTime 2022/05/18 16:59:00
 */
@Getter
@Setter
public class CommonPage <T>{
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }
}
