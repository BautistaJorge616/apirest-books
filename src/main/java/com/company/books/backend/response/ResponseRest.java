package com.company.books.backend.response;

import java.util.*;

public class ResponseRest {
	
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();
	
	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;
	}
	
	public void setMetadate(String type, String code, String date) {
		HashMap<String, String> map = new HashMap<>();
		
		map.put("Type", type);
		map.put("Code", code);
		map.put("Date", date);
		
		metadata.add(map);
	}
	
	
}
