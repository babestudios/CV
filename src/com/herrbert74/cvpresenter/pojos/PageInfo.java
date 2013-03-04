package com.herrbert74.cvpresenter.pojos;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Parcel;
import android.os.Parcelable;

public class PageInfo implements Parcelable {
	int mId;
	String mName;
	String mDescription;
	ArrayList<LineOfInformation> mLines;

	public int describeContents() {
		return 0;
	}

	public PageInfo(int mId, String mName, String mDescription, ArrayList<LineOfInformation> mLines) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mDescription = mDescription;
		this.mLines = mLines;
		Collections.sort(mLines);
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(mId);
		out.writeString(mName);
		out.writeString(mDescription);
		out.writeList(mLines);
	}

	public static final Parcelable.Creator<PageInfo> CREATOR = new Parcelable.Creator<PageInfo>() {
		public PageInfo createFromParcel(Parcel in) {
			return new PageInfo(in);
		}

		public PageInfo[] newArray(int size) {
			return new PageInfo[size];
		}
	};

	public PageInfo(){
		mId = 0;
		mName = "";
		mDescription = "";
		mLines = new ArrayList<LineOfInformation>();
	}
	
	private PageInfo(Parcel in) {
		mId = in.readInt();
		mName = in.readString();
		mDescription = in.readString();
		in.readList(mLines, null);
		Collections.sort(mLines);
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public ArrayList<LineOfInformation> getLines() {
		return mLines;
	}

	public void setLines(ArrayList<LineOfInformation> lines) {
		this.mLines = lines;
		Collections.sort(mLines);
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		this.mDescription = description;
	}
}
