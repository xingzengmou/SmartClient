package com.coopwell.smartclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coopwell.smartclient.R;
import com.coopwell.smartclient.mode.AcAdapterInfo;
import java.util.ArrayList;

public class AcAdapter extends ArrayAdapter<AcAdapterInfo> {
	private LayoutInflater inflater;

	private ArrayList<AcAdapterInfo> mList;

	public AcAdapter(Context paramContext, ArrayList<AcAdapterInfo> paramArrayList) {
		super(paramContext, R.layout.ac_list_item);
		LayoutInflater localLayoutInflater = LayoutInflater.from(paramContext);
		this.inflater = localLayoutInflater;
		this.mList = paramArrayList;
	}

	public int getCount() {
		return this.mList.size();
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		HolerView localHolerView = null;
		if (paramView == null) {
			localHolerView = new HolerView();
			paramView = this.inflater.inflate(R.layout.ac_list_item, null);
			localHolerView.txtName=(TextView)paramView.findViewById(R.id.ac_name);
			paramView.setTag(localHolerView);
		}
		localHolerView = (HolerView) paramView.getTag();
		localHolerView.id = ((AcAdapterInfo) this.mList.get(paramInt)).id;
		TextView localTextView2 = localHolerView.txtName;
		localTextView2.setText(String.valueOf(((AcAdapterInfo) this.mList
				.get(paramInt)).name));
		return paramView;
		
	}

	public final class HolerView {
		public int id;
		public TextView txtName;

	}
}