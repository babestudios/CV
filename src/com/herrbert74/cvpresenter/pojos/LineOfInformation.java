package com.herrbert74.cvpresenter.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The Class LineOfInformation.
 */
public class LineOfInformation implements Parcelable, Comparable<LineOfInformation> {
	
	/** The id_line. */
	int mId_line;
	
	/** The style. */
	int mStyle;
	
	/** The caption. */
	String mCaption;
	
	/** The text. */
	String mText;
	
	/** The detail. */
	String mDetail;
	
	/** The link. */
	String mLink;
	
	/**
	 * Instantiates a new line of information.
	 *
	 * @param id_line the id_line
	 * @param style the style
	 * @param caption the caption
	 * @param text the text
	 * @param detail the detail
	 * @param link the link
	 */
	public LineOfInformation(int id_line, int style, String caption, String text, String detail, String link) {
		mId_line = id_line;
		mStyle = style;
		mCaption = caption;
		mText = text;
		mDetail = detail;
		mLink = link;
	}

	/**
	 * Instantiates a new line of information.
	 *
	 * @param in the in
	 */
	public LineOfInformation(Parcel in) {
		mId_line = in.readInt();
		mStyle = in.readInt();
		mCaption = in.readString();
		mText = in.readString();
		mDetail = in.readString();
		mLink = in.readString();		
	}

	public int getmId_line() {
		return mId_line;
	}

	public void setmId_line(int mId_line) {
		this.mId_line = mId_line;
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

	/* (non-Javadoc)
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId_line);
		dest.writeInt(mStyle);
		dest.writeString(mCaption);
		dest.writeString(mText);
		dest.writeString(mDetail);
		dest.writeString(mLink);
		
	}

	/** The Constant CREATOR. */
	public static final Parcelable.Creator<LineOfInformation> CREATOR = new Parcelable.Creator<LineOfInformation>() {
		public LineOfInformation createFromParcel(Parcel in) {
			return new LineOfInformation(in);
		}

		public LineOfInformation[] newArray(int size) {
			return new LineOfInformation[size];
		}
	};

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(LineOfInformation another) {
		return this.getmId_line() - another.getmId_line();
	}
}
