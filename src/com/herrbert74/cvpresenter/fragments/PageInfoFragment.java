package com.herrbert74.cvpresenter.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.herrbert74.cvpresenter.CVApp;
import com.herrbert74.cvpresenter.CVConstants;
import com.herrbert74.cvpresenter.R;
import com.herrbert74.cvpresenter.adapters.LineOfInformationAdapter;
import com.herrbert74.cvpresenter.pojos.PageInfo;

public class PageInfoFragment extends Fragment implements CVConstants {
	
	int mPosition;
	String mTitle;
	Typeface mFont_buttons;
	static PageInfo mPageInfo;
	ImageView iv;

	public static PageInfoFragment newInstance(int position, PageInfo pageInfo) {
		PageInfoFragment f = new PageInfoFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		args.putParcelable("pageinfo", pageInfo);
		f.setArguments(args);

		// WindowManager wm = (WindowManager) CVApp.getContext().getSystemService(Context.WINDOW_SERVICE);
		// Display display = wm.getDefaultDisplay();
		// DisplayMetrics size = new DisplayMetrics();
		// display.getMetrics(size);
		// int width = size.widthPixels;
		// float substraction = TypedValue
		// .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, CVApp.getContext().getResources().getDisplayMetrics());

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPosition = getArguments() != null ? getArguments().getInt("position") : 1;
		mPageInfo = getArguments() != null ? (PageInfo) getArguments().getParcelable("pageinfo") : null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_pageinfo, container,
				false);
		TextView lbl = (TextView) v.findViewById(R.id.lbl);
		lbl.setTypeface(CVApp.getFontHeaders());
		ListView listview = (ListView) v.findViewById(R.id.listview);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try{
					TextView lbl_link = (TextView) arg1.findViewById(R.id.lbl_link);
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(lbl_link.getText().toString()));
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					CVApp.getContext().startActivity(i);
				}catch(Exception e){}
				
				
				Log.d("cv", Integer.toString(arg2));
				
			}
		});
		
		Context c = CVApp.getContext();
		LineOfInformationAdapter adapter = new LineOfInformationAdapter(c, mPageInfo.getLines());
		listview.setAdapter(adapter);
		lbl.setText(mPageInfo.getName());
		
		iv = (ImageView) v.findViewById(R.id.iv);
		return v;
	}
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int resID = BACKGROUNDS_ANDROID[mPosition];
        iv.setImageResource(resID);
    }
}