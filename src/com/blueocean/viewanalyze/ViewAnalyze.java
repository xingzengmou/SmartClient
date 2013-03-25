package com.blueocean.viewanalyze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.blueocean.controlcmd.QueryCmd;
import com.blueocean.viewex.AirViewEx;
import com.blueocean.viewex.ButtonEx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;

public class ViewAnalyze {
	private static final String TAG = "ViewAnalyze";
	private Context mContext;
	
	public ViewAnalyze(Context context) {
		this.mContext = context;
	}
	
	@SuppressWarnings("unused")
	private String getValue(String line) {
		String val = line.substring(line.indexOf("=") + 1, line.indexOf("#") != -1 ? line.indexOf("#") : line.length());
		Log.e(TAG, " VAL = " + val);
		return val.trim();
	}
	
	@SuppressLint({ "NewApi", "NewApi" })
	@SuppressWarnings("finally")
	public List<ButtonEx> getMainControlButtons() {
		List<ButtonEx> btnList = new ArrayList<ButtonEx>();
		try {
			InputStream is = mContext.getAssets().open("main_control/main.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String btnFileName = br.readLine();
			br.close();
			is.close();
			is = mContext.getAssets().open("main_control/" + btnFileName);
			br = new BufferedReader(new InputStreamReader(is));
			String temp = br.readLine();
			ButtonEx btnEx = new ButtonEx(mContext);;
			while (temp != null) {
				if (temp.isEmpty()) {
					temp = br.readLine();
					continue;
				}
				if (temp.contains("button")) {
					btnEx.setText(getValue(temp));
				} else if (temp.contains("btn_type")) {
					btnEx.setCmdName(getValue(temp));
				} else if (temp.contains("cmdtype")) {
					String val = getValue(temp);
					if (val.equals("8101")) {
						btnEx.setCmdType(0x8101);
					} else if (val.equals("8102")) {
						btnEx.setCmdType(0x8102);
					}
				} else if (temp.contains("hardwareid")) {
					String val = getValue(temp);
					btnEx.setHardwareId(Integer.parseInt(val));
				} else if (temp.contains("targetcode")) {
					String val = getValue(temp);
					btnEx.setTargetCode(Integer.parseInt(val));
				} else if (temp.equals("end")) {
					btnEx.setBackgroundResource(com.coopwell.smartclient.R.drawable.btn_control);
					btnEx.setGravity(Gravity.CENTER);
					btnEx.setTextColor(0xff000000);
					btnEx.setTextSize(mContext.getResources().getDimension(com.coopwell.smartclient.R.dimen.btn_text_size));
					btnList.add(btnEx);
					btnEx = new ButtonEx(mContext); 
				} 
				temp = br.readLine();
			}
			br.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return btnList;
		}
	}
	
	public List<QueryCmd> getQueryCmd(List<ButtonEx> list) {
		List<QueryCmd> qList = new ArrayList<QueryCmd>();
		for (ButtonEx be: list) {
			if (qList.size() == 0) {
				QueryCmd qc = new QueryCmd();
				qc.cmdName = be.getCmdName();
				qc.hardwareID = be.getHardwareId();
				qList.add(qc);
			} else {
				boolean flag = false;
				for (QueryCmd qc: qList) {
					if (qc.hardwareID == be.getHardwareId()) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					QueryCmd qc = new QueryCmd();
					qc.cmdName = be.getCmdName();
					qc.hardwareID = be.getHardwareId();
					qList.add(qc);
				}
			}
		}
		return qList;
	}
	
	@SuppressLint({ "NewApi", "NewApi" })
	@SuppressWarnings("finally")
	public List<AirViewEx>  getAirView() {
		List<AirViewEx> airViewList = new ArrayList<AirViewEx>();
		try {
			InputStream is = mContext.getAssets().open("air_control/main.txt");
			BufferedReader br  = new BufferedReader(new InputStreamReader(is));
			String cFileName = br.readLine();
			br.close();
			is.close();
			is = mContext.getAssets().open("air_control/" + cFileName);
			br = new BufferedReader(new InputStreamReader(is));
			String temp = br.readLine();
			AirViewEx mAirViewEx = new AirViewEx(mContext);
			while (temp != null) {
				if (temp.isEmpty()) {
					temp = br.readLine();
					continue;
				}
				if (temp.contains("kongtiao")) {
					mAirViewEx.setName(getValue(temp));
				} else if (temp.contains("hardwareid")) {
					mAirViewEx.setHardwareId(Integer.parseInt(getValue(temp)));
				} else if (temp.equals("end")) {
					airViewList.add(mAirViewEx);
					mAirViewEx = new AirViewEx(mContext);
				}
				temp = br.readLine();
			}
			br.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return airViewList;
		}
	}
	
	@SuppressLint({ "NewApi", "NewApi" })
	@SuppressWarnings("finally")
	public List<ButtonEx> getServiceButtons() {
		List<ButtonEx> btnList = new ArrayList<ButtonEx>();
		try {
			InputStream is = mContext.getAssets().open("service/main.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String btnFileName = br.readLine();
			br.close();
			is.close();
			is = mContext.getAssets().open("service/" + btnFileName);
			br = new BufferedReader(new InputStreamReader(is));
			String temp = br.readLine();
			ButtonEx btnEx = new ButtonEx(mContext);;
			while (temp != null) {
				if (temp.isEmpty()) {
					temp = br.readLine();
					continue;
				}
				if (temp.contains("button")) {
					btnEx.setText(getValue(temp));
				} else if (temp.contains("btn_type")) {
					btnEx.setCmdName(getValue(temp));
				} else if (temp.contains("cmdtype")) {
					String val = getValue(temp);
					if (val.equals("8101")) {
						btnEx.setCmdType(0x8101);
					} else if (val.equals("8102")) {
						btnEx.setCmdType(0x8102);
					}
				} else if (temp.contains("hardwareid")) {
					String val = getValue(temp);
					btnEx.setHardwareId(Integer.parseInt(val));
				} else if (temp.contains("targetcode")) {
					String val = getValue(temp);
					btnEx.setTargetCode(Integer.parseInt(val));
				} else if (temp.equals("end")) {
					btnEx.setBackgroundResource(com.coopwell.smartclient.R.drawable.btn_control);
					btnEx.setGravity(Gravity.CENTER);
					btnEx.setTextColor(0xff000000);
					btnEx.setTextSize(mContext.getResources().getDimension(com.coopwell.smartclient.R.dimen.btn_text_size));
					btnList.add(btnEx);
					btnEx = new ButtonEx(mContext); 
				} 
				temp = br.readLine();
			}
			br.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return btnList;
		}
	}
	
	@SuppressLint({ "NewApi", "NewApi" })
	@SuppressWarnings("finally")
	public List<ButtonEx> getCurtainMusicButtons() {
		List<ButtonEx> btnList = new ArrayList<ButtonEx>();
		try {
			InputStream is = mContext.getAssets().open("curtain_music/main.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String btnFileName = br.readLine();
			br.close();
			is.close();
			is = mContext.getAssets().open("curtain_music/" + btnFileName);
			br = new BufferedReader(new InputStreamReader(is));
			String temp = br.readLine();
			ButtonEx btnEx = new ButtonEx(mContext);;
			while (temp != null) {
				if (temp.isEmpty()) {
					temp = br.readLine();
					continue;
				}
				if (temp.contains("button")) {
					btnEx.setText(getValue(temp));
				} else if (temp.contains("btn_type")) {
					btnEx.setCmdName(getValue(temp));
				} else if (temp.contains("cmdtype")) {
					String val = getValue(temp);
					if (val.equals("8101")) {
						btnEx.setCmdType(0x8101);
					} else if (val.equals("8102")) {
						btnEx.setCmdType(0x8102);
					}
				} else if (temp.contains("hardwareid")) {
					String val = getValue(temp);
					btnEx.setHardwareId(Integer.parseInt(val));
				} else if (temp.contains("targetcode")) {
					String val = getValue(temp);
					btnEx.setTargetCode(Integer.parseInt(val));
				} else if (temp.equals("end")) {
					btnEx.setBackgroundResource(com.coopwell.smartclient.R.drawable.btn_control);
					btnEx.setGravity(Gravity.CENTER);
					btnEx.setTextColor(0xff000000);
					btnEx.setTextSize(mContext.getResources().getDimension(com.coopwell.smartclient.R.dimen.btn_text_size));
					btnList.add(btnEx);
					btnEx = new ButtonEx(mContext); 
				} 
				temp = br.readLine();
			}
			br.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return btnList;
		}
	}
	
}
