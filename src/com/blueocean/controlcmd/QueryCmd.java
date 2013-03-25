package com.blueocean.controlcmd;

//这个类是存放将要获取相关的硬件状态的命令，比如single, light, aircontrol这些指令，具体由配置文件决定
public class QueryCmd {
	public QueryCmd() {
		
	}
	public int hardwareID = 0; //硬件ID
	public String cmdName = ""; //这个变量存放single, light, aircontrol, 用来指定指令类型
}
