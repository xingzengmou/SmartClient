package com.blueocean.netinterface;

public interface OnUDPReceiveFinishListener {
	public void onUDPReceiveFinish(String cmdType, byte[] msg);
}
