package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.FinalData;
import com.example.entity.FundNetVal;
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
public interface IFundNetValService extends IService<FundNetVal> {
  /**
   * 分页查询
   * @param pagingParameters 分页条件
   * @return 当前页数据(List<FinalData>)
   */
  public List<FinalData> PaginationSort(PagingParameters pagingParameters);
}
