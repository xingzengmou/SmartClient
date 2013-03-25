package com.blueocean.controlcmd;

public class SingleQueryCmd {
	private static final String TAG = "SingleQueryCmd";
	public static final String cmdType = "single";
	
	private static byte[] cmd = new byte[] {0x19, 0x19, 0x48, 0x4d, 0x49, 0x53, 0x00, 0x05, 0x00, 0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 
			0x00, 0x00, 0x37, 0x01, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 0x01, 0x00, 0x00};

	public static void setHardwareID(int id) {
		cmd[22] = (byte)id;
	}
	
	public static byte[] getCmd() {
		return cmd;
	}
}
