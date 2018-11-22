package com.guang.stock.listener;

import com.guang.stock.thread.RequestProcessorThreadPool;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.listener
 * @Description:
 * @date: 2018/11/22 14:33
 */
@Component
public class InitListener implements CommandLineRunner{

    @Override
    public void run(String... strings) throws Exception {
        RequestProcessorThreadPool.init();
    }
}
