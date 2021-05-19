package com.netposa.service;

/**
 * @author JuHan
 * @date 2021-05-07
 */
public interface DataEngine {
    /**
     * 创建运行时环境
     */
    void buildEnvironment() throws Exception;

    /**
     * 执行查询
     * @return
     */
     void query() throws Exception;

    /**
     * 执行写入
     * @return
     */
    void write() throws Exception;
}
