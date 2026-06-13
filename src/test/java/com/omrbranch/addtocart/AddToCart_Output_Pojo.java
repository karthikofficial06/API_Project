package com.omrbranch.addtocart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCart_Output_Pojo {

    public int status;
    public String message;
    public String currency;
    public int cart_count;
    public Datum data;
}
