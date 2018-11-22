package com.guang.stock.thread;

import com.guang.stock.request.ProductInventoryCacheRefreshRequest;
import com.guang.stock.request.ProductInventoryDBUpdateRequest;
import com.guang.stock.request.Request;
import com.guang.stock.request.RequestQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.thread
 * @Description:
 * @date: 2018/11/22 15:26
 */
public class RequestProcessorThread implements Callable<Boolean>{

    private ArrayBlockingQueue<Request> queue;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {

            while (true){
                Request request = queue.take();

                RequestQueue requestQueue = RequestQueue.getInstance();
                Map<Long, Boolean> flagMap = requestQueue.getFlagMap();
                if (request instanceof ProductInventoryDBUpdateRequest){
                    flagMap.put(request.getProductId(), false);
                }else if(request instanceof ProductInventoryCacheRefreshRequest){
                    // 强制刷新
                    if (((ProductInventoryCacheRefreshRequest)request).getForceRefresh()){
                        request.process();
                        continue;
                    }

                    Boolean flag = flagMap.get(request.getProductId());

                    // 如果flag是null
                    if(flag == null) {
                        flagMap.put(request.getProductId(), false);
                    }

                    // 如果是缓存刷新的请求，那么就判断，如果标识不为空，而且是true，就说明之前有一个这个商品的数据库更新请求
                    if(flag != null && flag) {
                        flagMap.put(request.getProductId(), false);
                    }

                    // 如果是缓存刷新的请求，而且发现标识不为空，但是标识是false
                    // 说明前面已经有一个数据库更新请求+一个缓存刷新请求了，大家想一想
                    if(flag != null && !flag) {
                        // 对于这种读请求，直接就过滤掉，不要放到后面的内存队列里面去了
                        continue;
                    }
                }
                logger.info("开始处理商品,商品id="+request.getProductId());
                request.process();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
