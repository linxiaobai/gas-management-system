package com.gms.service;

/**
 * Created by Kevin on 2015/5/8.
 */
public interface EnvironmentalParamService {
    /**
     * 模拟环境温度
     */
    void insertData();

    String queryAll();

    String queryBySize(Integer dataSize);
}
