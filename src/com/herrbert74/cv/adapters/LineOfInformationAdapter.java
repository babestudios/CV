package com.herrbert74.cv.adapters;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.herrbert74.cv.CVConstants;
import com.herrbert74.cv.R;
import com.herrbert74.cv.pojos.LineOfInformation;

public class LineOfInformationAdapter extends ArrayAdapter<LineOfInformation> implements CVConstants {
	Context mContext;
	private final List<LineOfInformation> mLines;
	Typeface mFont_titles, mFont_buttons;

	public LineOfInformationAdapter(Context context, List<LineOfInformation> lines) {
		super(context, R.layout.adapter_loi_list3, lines);
		mContext = context;
		mLines = lines;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView;
		LineOfInformation loi = mLines.get(position);
		if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list1")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list1, parent, false);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			lbl_text.setText(loi.getmText());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list2")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list2, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			cpn.setText(loi.getmCaption());
			lbl_text.setText(loi.getmText());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list3")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list3, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			TextView lbl_detail = (TextView) rowView.findViewById(R.id.lbl_detail);
			cpn.setText(loi.getmCaption());
			lbl_text.setText(loi.getmText());
			lbl_detail.setText(loi.getmDetail());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("header")) {
			rowView = inflater.inflate(R.layout.adapter_loi_header, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			cpn.setText(loi.getmCaption());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("detail")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list1, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			cpn.setText(loi.getmCaption());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("link1")) {
			rowView = inflater.inflate(R.layout.adapter_loi_link1, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl = (TextView) rowView.findViewById(R.id.lbl);
			// Linkify.addLinks(lbl, Linkify.ALL);
			cpn.setText(loi.getmCaption());
			lbl.setText(loi.getLink());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("link2")) {
			rowView = inflater.inflate(R.layout.adapter_loi_link1, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_detail = (TextView) rowView.findViewById(R.id.lbl_detail);
			TextView lbl_link = (TextView) rowView.findViewById(R.id.lbl_link);
			// Linkify.addLinks(lbl, Linkify.ALL);
			cpn.setText(loi.getmCaption());
			lbl_detail.setText(loi.getmDetail());
			lbl_link.setText(loi.getLink());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list_bar")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list3, parent, false);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			TextView lbl_detail = (TextView) rowView.findViewById(R.id.lbl_detail);
			lbl_text.setText(loi.getmText());
			lbl_detail.setText(loi.getmDetail());
		} else {
			rowView = inflater.inflate(R.layout.adapter_loi_list3, parent, false);
		}
		return rowView;
	}
}