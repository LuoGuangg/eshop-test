package com.guang.stock.request;

import com.guang.stock.entity.ProductInventory;
import com.guang.stock.service.ProductInventoryService;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.request
 * @Description:
 * @date: 2018/11/22 15:34
 */
public class ProductInventoryCacheRefreshRequest implements Request{

    private Long productId;

    private ProductInventoryService productInventoryService;

    private Boolean forceRefresh;


    public ProductInventoryCacheRefreshRequest(Long productId, ProductInventoryService productInventoryService,Boolean forceRefresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.forceRefresh = forceRefresh;
    }


    public Boolean getForceRefresh() {
        return forceRefresh;
    }

    @Override
    public void process() {
        ProductInventory pi = productInventoryService.findProductInventory(getProductId());

        productInventoryService.setProductInventoryCache(pi);
    }

    @Override
    public Long getProductId() {
        return productId;
    }
}
