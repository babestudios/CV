package com.herrbert74.cv.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.herrbert74.cv.CVApp;
import com.herrbert74.cv.CVConstants;
import com.herrbert74.cv.R;
import com.herrbert74.cv.adapters.LineOfInformationAdapter;
import com.herrbert74.cv.pojos.PageInfo;

public class PageInfoFragment extends Fragment implements CVConstants {
	int mPosition;
	String mTitle;
	Typeface mFont_buttons;
	static PageInfo mPageInfo;

	public static PageInfoFragment newInstance(int position, PageInfo pageInfo) {
		PageInfoFragment f = new PageInfoFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		args.putParcelable("pageinfo", pageInfo);
		f.setArguments(args);

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
		
		Context c = CVApp.getContext();
		LineOfInformationAdapter adapter = new LineOfInformationAdapter(c, mPageInfo.getLines());
		listview.setAdapter(adapter);
		lbl.setText(mPageInfo.getName());
		return v;
	}
}

