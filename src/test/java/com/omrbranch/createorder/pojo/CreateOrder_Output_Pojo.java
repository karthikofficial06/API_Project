package com.omrbranch.createorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder_Output_Pojo {

	  public int status;
	    public String message;
	    public String currency;
	    public Datum data;
	    public int order_id;

}
