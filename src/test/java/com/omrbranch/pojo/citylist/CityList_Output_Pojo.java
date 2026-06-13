package com.omrbranch.pojo.citylist;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityList_Output_Pojo {
public int status;
    public String message;
    public ArrayList<CityList> data;
    
    
}
