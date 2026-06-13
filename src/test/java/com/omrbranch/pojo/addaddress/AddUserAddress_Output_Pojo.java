package com.omrbranch.pojo.addaddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserAddress_Output_Pojo {
	public int status;
	public String message;
	public  int address_id;
}
