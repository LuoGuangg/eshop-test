package com.guang.stock.dao;

import com.guang.stock.entity.ProductInventory;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.dao
 * @Description:
 * @date: 2018/11/22 10:04
 */
public interface ProductInventoryRepository extends PagingAndSortingRepository<ProductInventory, Long>{

    /**
    * 获得商品库存
    * @Author: LuoGuang
         * @Date: 2018/11/22 10:14
         *
     * @param productId   商品ID
         * @returns:
         */
    ProductInventory getByProductId(Long productId);

}
