package com.herrbert74.cvpresenter.adapters;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.herrbert74.cvpresenter.CVConstants;
import com.herrbert74.cvpresenter.R;
import com.herrbert74.cvpresenter.pojos.LineOfInformation;

/**
 * The Class LineOfInformationAdapter. Used to provide data for a CV page.
 */
public class LineOfInformationAdapter extends ArrayAdapter<LineOfInformation> implements CVConstants {
	
	/** The m context. */
	Context mContext;
	
	/** The data. */
	private final List<LineOfInformation> mLines;
	
	/** The m font_buttons. */
	Typeface mFont_titles, mFont_buttons;

	/**
	 * Instantiates a new line of information adapter.
	 *
	 * @param context the context
	 * @param lines the lines
	 */
	public LineOfInformationAdapter(Context context, List<LineOfInformation> lines) {
		super(context, R.layout.adapter_loi_list3, lines);
		mContext = context;
		mLines = lines;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView;
		LineOfInformation loi = mLines.get(position);
		//Image type
		if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("image")) {
			rowView = inflater.inflate(R.layout.adapter_loi_image, parent, false);
			AQuery aq = new AQuery(rowView);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			cpn.setText(loi.getmCaption());
			lbl_text.setText(loi.getmText());
			try {
				aq.id(R.id.iv).image(loi.getLink());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//List type with 1 data element
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list1")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list1, parent, false);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			lbl_text.setText(loi.getmText());
		}
		//List type with 2 data element
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list2")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list2, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			cpn.setText(loi.getmCaption());
			lbl_text.setText(loi.getmText());
		}
		//List type with 3 data element
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list3")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list3, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			TextView lbl_detail = (TextView) rowView.findViewById(R.id.lbl_detail);
			cpn.setText(loi.getmCaption());
			lbl_text.setText(loi.getmText());
			lbl_detail.setText(loi.getmDetail());
		}
		//List type with header data
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("header")) {
			rowView = inflater.inflate(R.layout.adapter_loi_header, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			cpn.setText(loi.getmCaption());
		}
		//List type with detail data
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("detail")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list1, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			cpn.setText(loi.getmCaption());
		}
		//List type with link
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("link1")) {
			rowView = inflater.inflate(R.layout.adapter_loi_link2, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_link = (TextView) rowView.findViewById(R.id.lbl_link);
			cpn.setText(loi.getmCaption());
			SpannableString content = new SpannableString(loi.getLink());
			content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
			lbl_link.setText(loi.getLink());
		}
		//List type with link
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("link2")) {
			rowView = inflater.inflate(R.layout.adapter_loi_link2, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_detail = (TextView) rowView.findViewById(R.id.lbl_detail);
			TextView lbl_link = (TextView) rowView.findViewById(R.id.lbl_link);
			cpn.setText(loi.getmCaption());
			lbl_detail.setText(loi.getmDetail());
			lbl_link.setText(loi.getLink());
		}
		//List type with list bar data
		else if (loi.getmStyle() == Arrays.asList(CV_LINE_STYLES).indexOf("list_bar")) {
			rowView = inflater.inflate(R.layout.adapter_loi_list_bar, parent, false);
			TextView cpn = (TextView) rowView.findViewById(R.id.cpn);
			TextView lbl_text = (TextView) rowView.findViewById(R.id.lbl_text);
			ImageView iv = (ImageView) rowView.findViewById(R.id.iv);
			cpn.setText(loi.getmText());
			lbl_text.setText(loi.getmDetail());
			float bar_width = 0;
			float bar_right_margin = 0;
			DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
			int dipValue = TypedValue.COMPLEX_UNIT_DIP;
			if (loi.getmDetail().equals("beginner")) {
				float dim_width = mContext.getResources().getDimension(R.dimen.list_bar_width_beginner);
				bar_width = TypedValue.applyDimension(dipValue, dim_width, displayMetrics);
				float dim_margin = mContext.getResources().getDimension(R.dimen.list_bar_margin_beginner);
				bar_right_margin = TypedValue.applyDimension(dipValue, dim_margin, displayMetrics);
				iv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_skill_beginner));
			} else if (loi.getmDetail().equals("intermediate")) {
				float dim_width = mContext.getResources().getDimension(R.dimen.list_bar_width_intermediate);
				bar_width = TypedValue.applyDimension(dipValue, dim_width, displayMetrics);
				float dim_margin = mContext.getResources().getDimension(R.dimen.list_bar_margin_intermediate);
				bar_right_margin = TypedValue.applyDimension(dipValue, dim_margin, displayMetrics);
				iv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_skill_intermediate));
			} else if (loi.getmDetail().equals("advanced")) {
				float dim_width = mContext.getResources().getDimension(R.dimen.list_bar_width_advanced);
				bar_width = TypedValue.applyDimension(dipValue, dim_width, displayMetrics);
				float dim_margin = mContext.getResources().getDimension(R.dimen.list_bar_margin_advanced);
				bar_right_margin = TypedValue.applyDimension(dipValue, dim_margin, displayMetrics);
				iv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_skill_advanced));
			} else if (loi.getmDetail().equals("expert")) {
				float dim_width = mContext.getResources().getDimension(R.dimen.list_bar_width_expert);
				bar_width = TypedValue.applyDimension(dipValue, dim_width, displayMetrics);
				float dim_margin = mContext.getResources().getDimension(R.dimen.list_bar_margin_expert);
				bar_right_margin = TypedValue.applyDimension(dipValue, dim_margin, displayMetrics);
				iv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_skill_expert));
			}
			float dim_height = mContext.getResources().getDimension(R.dimen.list_bar_height);
			float bar_height = TypedValue.applyDimension(dipValue, dim_height, displayMetrics);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv.getLayoutParams();
			params.height = (int) bar_height;
			params.width = (int) bar_width;
			params.rightMargin = (int) bar_right_margin;
			iv.setLayoutParams(params);
		} else {
			rowView = inflater.inflate(R.layout.adapter_loi_list3, parent, false);
		}
		return rowView;
	}
}