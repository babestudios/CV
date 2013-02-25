package com.herrbert74.cv.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class LineOfInformation implements Parcelable {
	int mStyle;
	String mImage;
	String mCaption;
	String mText;
	String mLevel;

	public LineOfInformation(int style, String image, String caption, String text, String level) {
		mStyle = style;
		mImage = image;
		mCaption = caption;
		mText = text;
		mLevel = level;
	}

	public LineOfInformation(Parcel in) {
		mStyle = in.readInt();
		mImage = in.readString();
		mCaption = in.readString();
		mText = in.readString();
		mLevel = in.readString();
	}

	public String getImage() {
		return mImage;
	}

	public void setImage(String image) {
		this.mImage = image;
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

	public String getmLevel() {
		return mLevel;
	}

	public void setmText(String mText) {
		this.mText = mText;
	}
	
	public void setmLevel(String level) {
		this.mLevel = level;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mStyle);
		dest.writeString(mCaption);
		dest.writeString(mImage);
		dest.writeString(mText);
		dest.writeString(mLevel);
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
