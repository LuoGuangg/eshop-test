package com.guang.stock.service.impl;

import com.guang.stock.request.Request;
import com.guang.stock.request.RequestQueue;
import com.guang.stock.service.RequestAsyncProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.service.impl
 * @Description:
 * @date: 2018/11/22 15:44
 */
@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void process(Request request) {
        try {
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
            // 将请求放入对应的队列中，完成路由操作
            queue.put(request);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayBlockingQueue<Request> getRoutingQueue(Long productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();

        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        // 对hash值取模，将hash值路由到指定的内存队列中，比如内存队列大小8
        // 用内存队列的数量对hash值取模之后，结果一定是在0~7之间
        // 所以任何一个商品id都会被固定路由到同样的一个内存队列中去的
        int index = (requestQueue.queueSize() - 1) & hash;

        logger.info("路由内存队列，商品id=" + productId + ", 队列索引=" + index);

        return requestQueue.getQueue(index);
    }


}
