package com.guang.stock.service;

import com.guang.stock.entity.ProductInventory;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.service
 * @Description:
 * @date: 2018/11/22 10:06
 */
public interface ProductInventoryService {

    /**
    * 更新商品库存
    * @Author: LuoGuang
         * @Date: 2018/11/22 10:08
         *
     * @param productInventory   商品库存
         */
    void updateProductInventory(ProductInventory productInventory);

    /**
    * 删除Redis中的商品库存的缓存
    * @Author: LuoGuang
         * @Date: 2018/11/22 10:08
         *
     * @param productInventory 商品库存
         */
    void removeProductInventoryCache(ProductInventory productInventory);

    /**
    * 根据商品id查询商品库存
    * @Author: LuoGuang
         * @Date: 2018/11/22 10:08
         *
     * @param productId 商品id
         * @returns:    商品库存
         */
    ProductInventory findProductInventory(Long productId);

    /**
    * 设置商品库存的缓存
    * @Author: LuoGuang
         * @Date: 2018/11/22 10:09
         *
     * @param productInventory 商品库存
         */
    void setProductInventoryCache(ProductInventory productInventory);

    /**
    * 获取商品库存的缓存
    * @Author: LuoGuang
         * @Date: 2018/11/22 10:09
         *
     * @param productId   商品ID
         * @returns:
         */
    ProductInventory getProductInventoryCache(Long productId);
}
