package com.omrbranch.createorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
	public class Country{
	    public int id;
	    public String code;
	    public String name;
	    public int phonecode;
	    public String currency_name;
	    public String currency_symbol;
	    public String currency_code;
	    public String status;
	}
