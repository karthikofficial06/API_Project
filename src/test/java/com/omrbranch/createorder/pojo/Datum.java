package com.omrbranch.createorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Datum{
    public String order_code;
    public int order_no;
    public int user_id;
    public int address_id;
    public String payment_method;
    public String wallet;
    public String updated_at;
    public String created_at;
    public int id;
    public String total_amount;
    public String coupon_discount;
    public String shipping_fee;
    public String grand_total;
    public String savings;
    public String credits_used;
    public int amount;
    public UserAddress user_address;
}
