package com.guang.stock;

import com.guang.stock.entity.ProductInventory;
import com.guang.stock.service.ProductInventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock
 * @Description:
 * @date: 2018/11/22 10:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInventoryTest {

    @Autowired
    private ProductInventoryService productInventoryService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void findProductInventory(){
        ProductInventory productInventory =  productInventoryService.findProductInventory(1L);
        logger.info(productInventory.toString());
    }

    @Test
    public void updateProductInventory(){
        ProductInventory productInventory = new ProductInventory(1L,10);
        productInventoryService.updateProductInventory(productInventory);
    }


    @Test
    public void setProductInventoryCache() {
        ProductInventory productInventory = new ProductInventory(1L,10);
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Test
    public void getProductInventoryCache() {
        Long productId = 1L;
        ProductInventory productInventory = productInventoryService.getProductInventoryCache(productId);
        logger.info(productInventory.toString());
    }

    @Test
    public void removeProductInventoryCache() {
        ProductInventory productInventory = new ProductInventory(Long.valueOf(1),10);
        productInventoryService.removeProductInventoryCache(productInventory);
    }


}
