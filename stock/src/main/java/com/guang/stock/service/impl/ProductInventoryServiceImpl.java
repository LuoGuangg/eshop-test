package com.guang.stock.service.impl;

import com.guang.stock.dao.ProductInventoryRepository;
import com.guang.stock.dao.RedisDao;
import com.guang.stock.entity.ProductInventory;
import com.guang.stock.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.service.impl
 * @Description:
 * @date: 2018/11/22 10:07
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService{

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private RedisDao redisDao;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        ProductInventory p = productInventoryRepository.getByProductId(productInventory.getProductId());
        p.setInventoryCnt(productInventory.getInventoryCnt());
        productInventoryRepository.save(p);

        logger.info("修改数据库商品存库：" + productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.delete(key);
        logger.info("Redis删除商品存库：" + key);
    }

    @Override
    public ProductInventory findProductInventory(Long productId) {
        logger.info("从数据库中查询商品存库，商品ID：" + productId);
        return productInventoryRepository.getByProductId(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.set(key,String.valueOf(productInventory.getInventoryCnt()));
        logger.info("设置Redis的商品存库，商品：" + productInventory);
    }

    @Override
    public ProductInventory getProductInventoryCache(Long productId) {
        String key = "product:inventory:" + productId;
        String result = redisDao.get(key);
        if(result != null && !"".equals(result)) {
            try {
                int inventoryCnt = Integer.valueOf(result);
                logger.info("从Redis中查询商品存库，商品ID：" + productId+"-存库："+inventoryCnt);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
