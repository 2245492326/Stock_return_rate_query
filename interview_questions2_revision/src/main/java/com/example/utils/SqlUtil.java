package com.example.utils;

import com.example.entity.FinalData;
import com.example.mapper.FinalDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取数据库全部值，更新bean
 * @author Administrator
 */
@Component
public class SqlUtil {
    @Autowired
    private FinalDataMapper finalDataMapper;

    private List<FinalData> finalDataList = new ArrayList<>();

    @Bean
    public List<FinalData> gitAllUtil() {
        finalDataList = finalDataMapper.selectList(null);
        return finalDataList;
    }
}