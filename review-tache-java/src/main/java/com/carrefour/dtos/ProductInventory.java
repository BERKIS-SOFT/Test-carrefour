package com.carrefour.dtos;

import lombok.Data;

@Data
public   class ProductInventory {
    private final String pName;
    private int qty;
    private float totalPrice;
    private String productBarcodeList;

    public ProductInventory(String pName, float totalPrice, int qty) {
        this.pName = pName;
        this.totalPrice = totalPrice;
        this.qty = qty;
    }
}
