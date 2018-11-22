package com.guang.stock.entity;

import javax.persistence.*;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.entity
 * @Description:
 * @date: 2018/11/22 9:57
 */
@Entity
@Table(name = "product_inventory")
public class ProductInventory {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;
    /**
     * 商品ID
     */
    @Column(name = "product_id")
    private Long productId;
    /**
     * 存库数量
     */
    @Column(name = "inventory_cnt")
    private Integer inventoryCnt;

    public ProductInventory(Long productId, Integer inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }

    public ProductInventory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Integer inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }

    @Override
    public String toString() {
        return "ProductInventory{" +
                "id=" + id +
                ", productId=" + productId +
                ", inventoryCnt=" + inventoryCnt +
                '}';
    }
}
