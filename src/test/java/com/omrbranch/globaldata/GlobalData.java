package com.omrbranch.globaldata;

import lombok.Data;

@Data
public class GlobalData {

    private int statusCode;
    private String logtoken;

    private int stateId;
    private int cityId;
    private int addressId;

    private String categoryId;
    private String productId;
    private String variantId;
    private String cartId;

    private static GlobalData globalData;

    private GlobalData() {
    }

    public static GlobalData getGlobalDataInstance() {
        if (globalData == null) {
            globalData = new GlobalData();
        }
        return globalData;
    }
    
    public String getCartId() {
    	return cartId;
    }

    public void setCartId(String cartId) {
    	this.cartId = cartId;
    }
}