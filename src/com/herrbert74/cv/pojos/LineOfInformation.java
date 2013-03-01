package com.herrbert74.cv.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class LineOfInformation implements Parcelable {
	int mStyle;
	String mCaption;
	String mText;
	String mDetail;
	String mLink;
	
	public LineOfInformation(int style, String caption, String text, String detail, String link) {
		mStyle = style;
		mCaption = caption;
		mText = text;
		mDetail = detail;
		mLink = link;
	}

	public LineOfInformation(Parcel in) {
		mStyle = in.readInt();
		mCaption = in.readString();
		mText = in.readString();
		mDetail = in.readString();
		mLink = in.readString();
			
	}

	public String getLink() {
		return mLink;
	}

	public void setLink(String link) {
		this.mLink = link;
	}

	public int getmStyle() {
		return mStyle;
	}

	public void setmStyle(int mStyle) {
		this.mStyle = mStyle;
	}

	public String getmCaption() {
		return mCaption;
	}

	public void setmCaption(String mCaption) {
		this.mCaption = mCaption;
	}

	public String getmText() {
		return mText;
	}

	public String getmDetail() {
		return mDetail;
	}

	public void setmText(String mText) {
		this.mText = mText;
	}
	
	public void setmDetail(String detail) {
		this.mDetail = detail;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mStyle);
		dest.writeString(mCaption);
		dest.writeString(mText);
		dest.writeString(mDetail);
		dest.writeString(mLink);
		
	}

	public static final Parcelable.Creator<LineOfInformation> CREATOR = new Parcelable.Creator<LineOfInformation>() {
		public LineOfInformation createFromParcel(Parcel in) {
			return new LineOfInformation(in);
		}

		public LineOfInformation[] newArray(int size) {
			return new LineOfInformation[size];
		}
	};
}
