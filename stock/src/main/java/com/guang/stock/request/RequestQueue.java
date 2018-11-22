package com.guang.stock.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.request
 * @Description:
 * @date: 2018/11/22 14:23
 */
public class RequestQueue{

    /**
     * 内存队列
     */
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    /**
     * 表示商品ID
     */
    private Map<Long, Boolean> flagMap  = new ConcurrentHashMap<>();


    private static class Singleton{

        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getInstance(){
            return instance;
        }


    }

    /**
     * 获得单利 RequestQueue
     * @return
     */
    public static RequestQueue getInstance(){
        return  Singleton.getInstance();
    }

    /**
     * 添加一个队列
     * @param queue 队列
     */
    public void addQueue(ArrayBlockingQueue<Request> queue){
        queues.add(queue);
    }

    /**
     * 队列数量
     * @return
     */
    public int queueSize(){
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueue(int index){
        return queues.get(index);
    }

    public Map<Long, Boolean> getFlagMap() {
        return flagMap;
    }


}
