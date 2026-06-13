package com.omrbranch.pojo.statelist;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class stateList_Output_Pojo {

	   public int status;
	    public String message;
	    public ArrayList<Datum> data;
}
