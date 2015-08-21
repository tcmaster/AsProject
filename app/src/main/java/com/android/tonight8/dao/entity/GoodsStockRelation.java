package com.android.tonight8.dao.entity;

import java.util.List;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/21 0021
 */
public class GoodsStockRelation {
    private long id;
    private GoodsStock goodsStock;
    private List<GoodsSpecification> goodsSpecifications;
    private List<GoodsStockItem> goodsStockItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GoodsStock getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(GoodsStock goodsStock) {
        this.goodsStock = goodsStock;
    }

    public List<GoodsSpecification> getGoodsSpecifications() {
        return goodsSpecifications;
    }

    public void setGoodsSpecifications(List<GoodsSpecification> goodsSpecifications) {
        this.goodsSpecifications = goodsSpecifications;
    }

    public List<GoodsStockItem> getGoodsStockItems() {
        return goodsStockItems;
    }

    public void setGoodsStockItems(List<GoodsStockItem> goodsStockItems) {
        this.goodsStockItems = goodsStockItems;
    }

    @Override
    public String toString() {
        return "GoodsStockRelation{" +
                "id=" + id +
                ", goodsStock=" + goodsStock +
                ", goodsSpecifications=" + goodsSpecifications +
                ", goodsStockItems=" + goodsStockItems +
                '}';
    }
}
