package com.blueocean.viewex;

import android.content.Context;

public class AirViewEx {
	private int hardwareId = 0;
	private String name = "";
	
	public AirViewEx(Context context) {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setHardwareId(int id) {
		this.hardwareId = id;
	}
	
	public int getHardwareId() {
		return this.hardwareId;
	}
}
