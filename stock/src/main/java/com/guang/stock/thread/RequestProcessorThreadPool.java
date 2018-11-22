package com.guang.stock.thread;

import com.guang.stock.request.Request;
import com.guang.stock.request.RequestQueue;

import java.util.concurrent.*;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.thread
 * @Description: 库存增加删除请求线程池
 * @date: 2018/11/22 14:37
 */
public class RequestProcessorThreadPool {

//    private ExecutorService threadPool = new ThreadPoolExecutor(10, Integer.MAX_VALUE,
//            60L, TimeUnit.SECONDS,
//            new SynchronousQueue<Runnable>());

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private RequestProcessorThreadPool(){
        RequestQueue requestQueue = RequestQueue.getInstance();
        for (int i = 0; i < 10; i++){
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    private static class Singleton{

        private static RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance(){
            return instance;
        }

    }

    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    public static void init(){
        getInstance();

    }




}
