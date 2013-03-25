package com.blueocean.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceConfig {
	private Context ctx;
	private SharedPreferences sp;
	private Editor e;
	
	public SharedPreferenceConfig(Context ctx) {
		this.ctx = ctx;
		sp = ctx.getSharedPreferences("smartclient", Context.MODE_PRIVATE);
		e = sp.edit();
	}
	
	public void writeBoolean(String key, boolean value) {
		e.putBoolean(key, (Boolean)value);
		e.commit();
	}
	
	public boolean getBoolean(String key) {
		return sp.getBoolean(key, true);
	}
	
	public boolean getBooleanFirst(String key) {
		return sp.getBoolean(key, false);
	}
	
	public int getInt(String key, int value) {
		return sp.getInt(key, value);
	}
	
	public void writeInt(String key, int value) {
		e.putInt(key, value);
		e.commit();
	}
	
	public String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}
	
	public void writeString(String key, String value) {
		e.putString(key, value);
		e.commit();
	}
}
