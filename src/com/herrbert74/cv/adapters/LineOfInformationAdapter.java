package com.herrbert74.cv.adapters;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
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
		super(context, R.layout.adapter_loi_list, lines);
		mContext = context;
		mLines = lines;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView;
		LineOfInformation loi = mLines.get(position);
		if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("picture")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl = (TextView) rowView.findViewById(R.id.lbl);
			cpn.setText(loi.getmCaption());
			lbl.setText(loi.getmText());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl = (TextView) rowView.findViewById(R.id.lbl);
			cpn.setText(loi.getmCaption());
			lbl.setText(loi.getmText());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("detail")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl = (TextView) rowView.findViewById(R.id.lbl);
			cpn.setText(loi.getmCaption());
			lbl.setText(loi.getmText());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("link")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl = (TextView) rowView.findViewById(R.id.lbl);
			cpn.setText(loi.getmCaption());
			lbl.setText(loi.getmText());
		} else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list_bar")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl = (TextView) rowView.findViewById(R.id.lbl);
			cpn.setText(loi.getmCaption());
			lbl.setText(loi.getmText());
		} else {
			rowView = inflater.inflate(R.layout.adapter_loi_list, parent, false);
		}
		return rowView;
	}
}