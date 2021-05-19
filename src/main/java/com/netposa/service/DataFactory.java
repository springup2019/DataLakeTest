package com.netposa.service;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author JuHan
 * @date 2021-05-10
 */
public class DataFactory {
    Queue<String> dataQueue;
    public DataFactory(){
        dataQueue= new LinkedList<>();
    }



    public void putData(String data){
        dataQueue.offer(data);
    }

    public String getdata(){
        return dataQueue.poll();
    }
}
