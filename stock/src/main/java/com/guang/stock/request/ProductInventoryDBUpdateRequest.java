package com.guang.stock.request;

import com.guang.stock.entity.ProductInventory;
import com.guang.stock.service.ProductInventoryService;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.request
 * @Description:
 * @date: 2018/11/22 15:31
 */
public class ProductInventoryDBUpdateRequest implements Request{

    private ProductInventory productInventory;

    private ProductInventoryService productInventoryService;


    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        productInventoryService.removeProductInventoryCache(productInventory);

        productInventoryService.updateProductInventory(productInventory);
    }

    @Override
    public Long getProductId() {
        return productInventory.getProductId();
    }
}
