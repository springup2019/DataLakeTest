package com.netposa.service.engine;

import com.netposa.common.Constants;
import com.netposa.service.DataEngine;
import com.netposa.utils.Global;
import java.sql.*;
import java.util.Optional;

/**
 * @author JuHan
 * @date 2021-05-07
 */
public class DataEngineHive implements DataEngine {
    private Connection connection = null;
    private Statement stmt = null;


    @Override
    public void buildEnvironment() throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        connection = DriverManager.getConnection(Global.HIVE_URL,Global.HIVE_USER, Global.HIVE_PASSWORD);
        stmt = connection.createStatement();
        //建写入表(如果hive.tb.write配置项为空，则每个线程自动生成张表；若不为空，则所有线程都写入同一张表)
        String tableName = Optional.ofNullable(Global.HIVE_TB_WRITE).orElse(Thread.currentThread().getName().replaceAll("-", "_"));
        String sqlDropTable = String.format("drop table if exists %s.%s", Global.HIVE_DB, tableName);
        stmt.execute(sqlDropTable);
        String sqlCreateTable = String.format(Constants.SQL_CREATE_TABLE, Global.HIVE_DB, tableName);
        stmt.execute(sqlCreateTable);
    }

    @Override
    public void query() throws Exception {
        String sql = String.format("select * from %s.%s", Global.HIVE_DB, Global.HIVE_TB_READ);
        stmt.execute(sql);
    }

    @Override
    public void write() throws Exception {
        String writeTo = Global.HIVE_DB + "." +  Optional.ofNullable(Global.HIVE_TB_WRITE)
                .orElse(Thread.currentThread().getName().replaceAll("-", "_"));
        String readFrom = Global.HIVE_DB + "." + Global.HIVE_TB_DATASOURCE;
        String selectData = String.format(Constants.SQL_SELECT, readFrom, Global.HIVE_DATA_LIMIT);
        String setReduceMemory = String.format("set mapreduce.reduce.memory.mb=%s", Global.MAPREDUCE_REDUCE_MEMORY_MB);
        stmt.execute(setReduceMemory);
        String setMapMemory = String.format("set mapreduce.reduce.memory.mb=%s", Global.MAPREDUCE_MAP_MEMORY_MB);
        stmt.execute(setMapMemory);

        String sql = String.format("insert %s table %s partition (dt_date='%s') %s", Global.WRITE_MODE, writeTo, Global.HIVE_DATA_PARTITIONS, selectData);
        System.out.println("执行HIVE SQL：" + sql);
        stmt.execute(sql);
    }
}
