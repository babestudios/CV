package com.herrbert74.cvpresenter.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.herrbert74.cvpresenter.R;
import com.herrbert74.cvpresenter.pojos.LineOfInformation;
import com.herrbert74.cvpresenter.pojos.PageInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLReader.
 */
public class XMLReader {

	/**
	 * Gets the CV pages from xml.
	 *
	 * @param context the context
	 * @return the pages from xml
	 * @throws XmlPullParserException the xml pull parser exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<PageInfo> getPagesFromXML(Context context) throws XmlPullParserException, IOException {
		ArrayList<PageInfo> pageInfos = new ArrayList<PageInfo>();
		PageInfo pageInfo = new PageInfo();
		ArrayList<LineOfInformation> lines = new ArrayList<LineOfInformation>();
		Resources res = context.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.data);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_DOCUMENT) {

			} else if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals("page")) {
					pageInfo = new PageInfo();
					lines = new ArrayList<LineOfInformation>();
					pageInfo.setId(Integer.parseInt(xpp.getAttributeValue(null, "id")));
					pageInfo.setName(xpp.getAttributeValue(null, "name"));
					pageInfo.setDescription(xpp.getAttributeValue(null, "description"));
				} else if (xpp.getName().equals("line")) {
					
					LineOfInformation line = new LineOfInformation(Integer.parseInt(xpp.getAttributeValue(null, "id_line")),Integer.parseInt(xpp.getAttributeValue(null, "style")), xpp.getAttributeValue(null, "image"), xpp.getAttributeValue(null, "caption"), xpp.getAttributeValue(null, "text"), xpp.getAttributeValue(null, "level"));
					lines.add(line);
				}

			} else if (eventType == XmlPullParser.END_TAG) {
				if (xpp.getName().equals("page")) {
					pageInfo.setLines(lines);
					pageInfos.add(pageInfo);
				}
			} else if (eventType == XmlPullParser.TEXT) {

			}
			eventType = xpp.next();
		}
		return pageInfos;
	}
}
