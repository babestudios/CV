package com.herrbert74.cv.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class LineOfInformation implements Parcelable {
	String mStyle;
	String mImage;
	String mCaption;
	String mText;

	public LineOfInformation(String style, String image, String caption, String text) {
		mStyle = style;
		mImage = image;
		mCaption = caption;
		mText = text;
	}

	public LineOfInformation(Parcel in) {
		mStyle = in.readString();
		mImage = in.readString();
		mCaption = in.readString();
		mText = in.readString();
	}

	public String getImage() {
		return mImage;
	}

	public void setImage(String image) {
		this.mImage = image;
	}

	public String getmStyle() {
		return mStyle;
	}

	public void setmStyle(String mStyle) {
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

	public void setmText(String mText) {
		this.mText = mText;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mStyle);
		dest.writeString(mCaption);
		dest.writeString(mImage);
		dest.writeString(mText);

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
