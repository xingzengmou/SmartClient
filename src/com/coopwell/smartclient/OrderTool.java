package com.coopwell.smartclient;

import com.coopwell.smartclient.mode.ACInfo;

public class OrderTool {

	/**
	 * 空调控制
	 */
	public byte[] getByteAC(byte[] mByte, byte paramByte,
			ACInfo info) {
		mByte[0] = 0x21;
		mByte[1] = 0x21;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;
		mByte[14] = 0x01;
		mByte[15] = 0x06;
		mByte[16] = 0x04;
		mByte[17] = 0x01;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		mByte[22] = info.getnSeasonMode();
		mByte[23] = info.getnSetTemp();
		mByte[24] = info.getnTempDate();
		mByte[25] = info.getnSpeed();
		mByte[26] = info.getnCtrl();
		mByte[27] = info.getnHalt();
		mByte[28] = info.getnValild();
		mByte[29] = info.getnState();
		mByte[30] = info.getnWorkState();
		return mByte;
	}

	/**
	 * 灯光控制命令 
	 * @param addId-地址
	 * @param id-回路号
	 */
	public byte[] getByteGetLighting(byte[] mByte, byte addId,
			byte id) {
		mByte[0] = 0x19;
		mByte[1] = 0x19;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;
		mByte[14] = addId;
		mByte[15] = (byte)0x81;
		mByte[16] = 0x12;
		mByte[17] = 0x01;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		mByte[22] = id;
		return mByte;
	}

	/**
	 * 查询空调命令
	 * @param addId-地址id
	 */
	public byte[] getByteQueryAC(byte[] mByte, byte addId) {
		mByte[0] = 0x18;
		mByte[1] = 0x18;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;;
		mByte[14] = addId;
		mByte[15] = 0x06;
		mByte[16] = 0x14;
		mByte[17] = 0x01;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		return mByte;
	}

	/**
	 * 单路灯光控制命令
	 */
	public byte[] getByteSendLighting(byte[] mByte, byte addId,
			byte id, byte state) {
		mByte[0] = 0x18;
		mByte[1] = 0x18;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;
		mByte[14] = addId;
		mByte[15] = (byte)0x81;
		mByte[16] = 0x02;
		mByte[17] = 0x01;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		mByte[22] = id;
		mByte[23] = state;
		mByte[24] = 0x00;
		mByte[25] = 0x00;
		mByte[26] = 0x00;
		mByte[27] = 0x00;
		return mByte;
	}

	/**
	 * 状态查询命令
	 */
	public byte[] getByteState(byte[] mByte) {
		mByte[0] = 0x16;
		mByte[1] = 0x19;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;
		mByte[14] = 0x00;
		mByte[15] = 0x36;
		mByte[16] = 0x00;
		mByte[17] = 0x01;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		return mByte;
	}

	/**
	 * 场景控制命令
	 * @param id-场景号
	 */
	public byte[] getSceneByte(byte[] mByte, int id) {
		mByte[0] = 0x1c;
		mByte[1] = 0x1c;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;
		mByte[14] = (byte)0xff;
		mByte[15] = (byte)0x81;
		mByte[16] = 0x01;
		mByte[17] = 0x00;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		mByte[22] = 0x00;
		mByte[23] = (byte)(((id-1)<<1)+1);
		mByte[24] = 0x00;
		mByte[25] = 0x00;
		mByte[26] = 0x00;
		mByte[27] = 0x00;
		return mByte;
	}

	/**
	 *  查询服务状态命令
	 */
	public byte[] getService(byte[] mByte) {
		mByte[0] = 0x16;
		mByte[1] = 0x19;
		mByte[2] = 0x48;
		mByte[3] = 0x4d;
		mByte[4] = 0x49;
		mByte[5] = 0x53;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[6] = 0x00;
		mByte[7] = 0x05;
		mByte[8] = 0x00;
		mByte[9] = 0x00;
		mByte[10] = (byte)0xff;
		mByte[11] = (byte)0xff;
		mByte[12] = (byte)0xff;
		mByte[13] = (byte)0xff;
		mByte[14] = 0x00;
		mByte[15] = 0x01;
		mByte[16] = 0x00;
		mByte[17] = 0x01;
		mByte[18] = (byte)0xff;
		mByte[19] = (byte)0xff;
		mByte[20] = (byte)0xff;
		mByte[21] = (byte)0xff;
		return mByte;
	}
}