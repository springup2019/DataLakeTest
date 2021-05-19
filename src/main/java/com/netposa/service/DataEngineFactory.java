package com.netposa.service;

import com.netposa.service.engine.DataEngineHive;
import com.netposa.service.engine.DataEngineHudi;
import com.netposa.service.engine.DataEngineIceberg;

/**
 * @author JuHan
 * @date 2021-05-07
 */
public class DataEngineFactory {
    public static DataEngine getDataEngineInstance(String dataEngineType) throws Exception {
        switch (dataEngineType) {
            case "hive":
                return createDataEngineInstance(DataEngineHive.class);
            case "iceberg":
                return createDataEngineInstance(DataEngineIceberg.class);
            case "hudi":
                return createDataEngineInstance(DataEngineHudi.class);
            default:
                throw new Exception("没有给定的数据存储引擎类型。输入的数据存储引擎类型："+dataEngineType);
        }
    }

    private static <T extends DataEngine> T createDataEngineInstance(Class<T> clazz) {
        DataEngine dataEngine = null;
        try {
            dataEngine = (DataEngine) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return (T) dataEngine;
    }
}
