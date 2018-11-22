package com.guang.stock.controller;

import com.guang.stock.entity.ProductInventory;
import com.guang.stock.request.ProductInventoryCacheRefreshRequest;
import com.guang.stock.request.ProductInventoryDBUpdateRequest;
import com.guang.stock.request.Request;
import com.guang.stock.service.ProductInventoryService;
import com.guang.stock.service.RequestAsyncProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.controller
 * @Description:
 * @date: 2018/11/22 10:10
 */
@RestController
@RequestMapping("/product/inventory")
public class ProductInventoryController {

    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    @Autowired
    private ProductInventoryService productInventoryService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/{id}")
    public ProductInventory findProductInventory(@PathVariable("id") Long id){
        logger.info("获取商品，商品ID="+id);
        ProductInventory pi;
        Request request;
        long startTime = System.currentTimeMillis();
        long endTime = 0L;
        long waitTime = 0L;


        request = new ProductInventoryCacheRefreshRequest(
                id, productInventoryService, false);
        requestAsyncProcessService.process(request);

        while (true){
            if (waitTime >= 200){
                break;
            }
            pi = productInventoryService.getProductInventoryCache(id);
            if (pi != null){
                return pi;
            }else{
                try {
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        pi = productInventoryService.findProductInventory(id);
        if (null != pi){

            request = new ProductInventoryCacheRefreshRequest(
                    id, productInventoryService, true);
            requestAsyncProcessService.process(request);

        }
        return pi;
    }


    /**
     * 更新商品库存
     */
    @RequestMapping("/updateProductInventory")
    public Boolean updateProductInventory(ProductInventory productInventory) {
        logger.info("接收到更新商品库存的请求，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());

        try {
            Request request = new ProductInventoryDBUpdateRequest(
                    productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
