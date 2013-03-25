package com.coopwell.smartclient;

public class CmdType {
	//空调控制
	public static final byte AC_CONTROL = 0x04;
	//空调查询
	public static final byte AC_QUERY = 0x14;
	//空调状态
	public static final byte AC_STATE = 0x36;
	public static final byte AC_STATE_CODE = 0x00;
	public static final byte AC_TYPE = 0x06;
	public static final byte ASK_RECEIVE = 0x01;
	public static final byte ASK_SUCCEED = 16;
	//场景or灯光
	public static final byte BYTE =(byte)0x81;
	//灯光状态获取
	public static final byte LIGHT_GET = 0x12;
	//灯光控制
	public static final byte LIGHT_SEND = 0x02;
	//场景
	public static final byte SCENE = 0x01;
	public static final byte SCENE_LIGHT = (byte)0x81;
	//服务
	public static final byte SERVICE = 0x01;
	public static final byte SERVICE_CODE = 0x00;

}