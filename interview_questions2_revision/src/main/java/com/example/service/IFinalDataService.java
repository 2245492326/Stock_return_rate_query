package com.example.service;

import com.example.entity.FinalData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.PagingParameters;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cgs
 * @since 2023-09-14
 */
public interface IFinalDataService extends IService<FinalData> {
    /**
     * 分页查询
     * @param pagingParameters 分页条件
     * @return 当前页数据(List<FinalData>)
     */
    public List<FinalData> paginationSort(PagingParameters pagingParameters);
}
