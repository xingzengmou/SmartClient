package com.blueocean.viewex;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class ButtonEx extends TextView {
	private byte[] cmdType = new byte[2];
	private String cmdName = "";
	private int hardwareId = 0;
	private int targetCode = 0;
	private boolean enabled;
	private int brightness = 0;

	public ButtonEx(Context context) {
		super(context);
		enabled = false;
	}
	
	public ButtonEx(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public ButtonEx(Context context, AttributeSet attrs, int style) {
		super(context, attrs, style);
	}
	
	public void setCmdType(int cmdType) {
		this.cmdType[0] = (byte) ((cmdType & 0xFF00) >> 8);
		this.cmdType[1] = (byte) (cmdType & 0x00FF);
	}
	
	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}
	
	public String getCmdName() {
		return this.cmdName;
	}
	
	public byte[] getCmdType() {
		return cmdType;
	}
	
	public void setHardwareId(int hid) {
		this.hardwareId = hid;
	}
	
	public int getHardwareId() {
		return this.hardwareId;
	}
	
	public void setTargetCode(int targetCode) {
		this.targetCode = targetCode;
	}
	
	public int getTargetCode() {
		return this.targetCode;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	public int getBrightness() {
		return this.brightness;
	}

}
