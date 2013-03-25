package com.coopwell.smartclient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.coopwell.smartclient.R;
import com.coopwell.smartclient.adapter.AcAdapter;
import com.coopwell.smartclient.adapter.AcAdapter.HolerView;
import com.coopwell.smartclient.mode.AcAdapterInfo;

import java.util.ArrayList;

public class SpinnerView {
	private LayoutInflater inflater;
	public boolean init;
	public boolean isShow;

	private ArrayList<AcAdapterInfo> list;
	AdapterView.OnItemClickListener listener;
	private AcAdapter mAdapter;
	private Context mContext;
	private ListView mListView;
	private PopupWindow mPopupWindow;
	private ISelectListener mSelectListener;
	private View mView;
	View.OnKeyListener onKeyListener;

	public SpinnerView(Context context) {
		inflater = LayoutInflater.from(context);
		mContext = context;
		list = new ArrayList<AcAdapterInfo>();
	}

	public void hideView() {
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
		isShow = false;
	}

	public void initView(int x, int y) {
		if (!init) {
			init = true;
			mView = this.inflater.inflate(R.layout.spinner_view, null);
			mListView = (ListView) this.mView.findViewById(R.id.air_list);
			mListView.setOnItemClickListener(new OnItemClickListener() {

//				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					HolerView holerView = (HolerView) view.getTag();
					if (mSelectListener != null) {
						mSelectListener.onSelect(holerView.id,
								holerView.txtName.getText().toString());
					}
				}
			});
			mAdapter = new AcAdapter(mContext, list);
			mListView.setAdapter(mAdapter);

			mListView.setFocusable(true);
			mPopupWindow = new PopupWindow(mView, x, y);
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.getContentView().setOnTouchListener(
					new OnTouchListener() {
//						@Override
						public boolean onTouch(View v, MotionEvent event) {
							mPopupWindow.dismiss();
							isShow = false;
							return false;
						}
					});
		}

	}

	public void setList(ArrayList<AcAdapterInfo> list) {
		this.list = list;
	}

	public void setmSelectListener(ISelectListener paramISelectListener) {
		this.mSelectListener = paramISelectListener;
	}

	public void showView(View paramView) {
		if (this.isShow)
			return;
		isShow = true;
		mPopupWindow.setFocusable(true);
		mPopupWindow.update();
		mPopupWindow.showAsDropDown(paramView, -1, -1);
	}

	public interface ISelectListener {
		void onSelect(int id, String name);
	}
}