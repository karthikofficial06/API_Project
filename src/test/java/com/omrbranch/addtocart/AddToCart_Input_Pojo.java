package com.omrbranch.addtocart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCart_Input_Pojo {
    public String product_id;
    public String product_variation_id;
    public String type;
	
}
