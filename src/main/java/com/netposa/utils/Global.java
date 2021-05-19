package com.netposa.utils;

public class Global {
    /**
     * @Fields TEST_MODE : hive执行动作[read,write]
     */
    public static final String TEST_MODE = Conf.get("test.mode");

    /**
     * @Fields TEST_DATAENGINE_NAME : 测试引擎[hive,iceberg,hudi]
     */
    public static final String TEST_DATAENGINE_NAME = Conf.get("test.dataEngine.name", "hive");

    /**
     * @Fields THREADPOOL_SIZE : 线程池大小[must bigger than 0]
     */
    public static final int THREADPOOL_SIZE = Conf.get("threadPool.size", 10);

    /**
     * @Fields WRITE_MODE : 写数据模式[read,write]
     */
    public static final String WRITE_MODE = Conf.get("write.mode");

    /**
     * @Fields MAPREDUCE_REDUCE_MEMORY_MB : reduce内存大小[默认30720]
     */
    public static final String MAPREDUCE_REDUCE_MEMORY_MB = Conf.get("mapreduce.reduce.memory.mb");

    /**
     * @Fields MAPREDUCE_MAP_MEMORY_MB : map内存大小[默认30720]
     */
    public static final String MAPREDUCE_MAP_MEMORY_MB = Conf.get("mapreduce.map.memory.mb");

    /**
     * @Fields HIVE_URL : HIVE_URL[jdbc:hive2://172.16.129.182:25000/]
     */
    public static final String HIVE_URL = Conf.get("hive.url");

    /**
     * @Fields HIVE_USER : hive执行用户[root]
     */
    public static final String HIVE_USER = Conf.get("hive.user", "");

    /**
     * @Fields HIVE_PASSWORD : hive执行密码[root]
     */
    public static final String HIVE_PASSWORD = Conf.get("hive.password", "");

    /**
     * @Fields HIVE_DB : hive库名[ods_kafka]
     */
    public static final String HIVE_DB = Conf.get("hive.db");

    /**
     * @Fields HIVE_TB_DATASOURCE : hive写入数据时读表[read,write]
     */
    public static final String HIVE_TB_DATASOURCE = Conf.get("hive.tb.datasource");

    /**
     * @Fields HIVE_TB_READ : hive查询读表[read,write]
     */
    public static final String HIVE_TB_READ = Conf.get("hive.tb.read");

    /**
     * @Fields HIVE_TB_WRITE : hive写数据表[ods_access_vehicle_features_write]
     */
    public static final String HIVE_TB_WRITE = Conf.get("hive.tb.write");

    /**
     * @Fields HIVE_DATA_PARTITIONS : hive写入数据时读表分区[2021-03-10]
     */
    public static final String HIVE_DATA_PARTITIONS = Conf.get("hive.data.partitions");

    /**
     * @Fields HIVE_DATA_PARTITIONS : hive操作数据条数[30000000]
     */
    public static final String HIVE_DATA_LIMIT = Conf.get("hive.data.limit");
}
