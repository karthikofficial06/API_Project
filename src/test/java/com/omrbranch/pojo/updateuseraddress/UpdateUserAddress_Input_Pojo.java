package com.omrbranch.pojo.updateuseraddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserAddress_Input_Pojo {
	public int address_id;
	public String first_name;
    public String last_name;
    public String mobile;
    public String apartment;
    public int state;
    public int city;
    public int country;
    public String zipcode;
    public String address;
    public String address_type;
}
