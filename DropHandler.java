package com.brightkeep.dropionotes;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DropHandler extends DefaultHandler {


	private String currentElementData;

	private Drop drop;


	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentElementData = "";
		if (localName.equalsIgnoreCase("drop")) {
			drop = new Drop();
		}

	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {

		currentElementData += new String(ch, start, length);

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		/*
		08-24 13:44:36.202: INFO/System.out(1081): http://api.drop.io/drops/?admin_password=carmen2&api_key=0e7824af253fab32035a978dc9ccc2f5c2336e9b&format=XML&name=CarmenDelessioPasswordTest&password=carmen1&version=1.0
			08-24 13:44:36.822: INFO/System.out(1081): <?xml version="1.0" encoding="UTF-8"?>
			08-24 13:44:36.822: INFO/System.out(1081): <drop>
			08-24 13:44:36.832: INFO/System.out(1081):   <guests_can_download type="boolean">true</guests_can_download>
			08-24 13:44:36.832: INFO/System.out(1081):   <conference> 218-486-3891 x 435827506</conference>
			08-24 13:44:36.832: INFO/System.out(1081):   <asset_count type="integer">0</asset_count>
			08-24 13:44:36.832: INFO/System.out(1081):   <current_bytes type="integer">0</current_bytes>
			08-24 13:44:36.832: INFO/System.out(1081):   <guests_can_add type="boolean">true</guests_can_add>
			08-24 13:44:36.832: INFO/System.out(1081):   <rss>http://drop.io/eudwt3lnew5himvw8one/bcdcfa4d0194bf3393903dff6c0a5a21ca8a3df0/CarmenDelessioPasswordTest.rss</rss>
			08-24 13:44:36.832: INFO/System.out(1081):   <fax>http://drop.io/CarmenDelessioPasswordTest/COGPLNK/Fax_Coverpage.pdf</fax>
			08-24 13:44:36.842: INFO/System.out(1081):   <guests_can_comment type="boolean">true</guests_can_comment>
			08-24 13:44:36.842: INFO/System.out(1081):   <guest_token>kwgzkma</guest_token>
			08-24 13:44:36.842: INFO/System.out(1081):   <guests_can_delete type="boolean">true</guests_can_delete>
			08-24 13:44:36.842: INFO/System.out(1081):   <admin_token>jtxfvfz0kk</admin_token>
			08-24 13:44:36.842: INFO/System.out(1081):   <email>CarmenDelessioPasswordTest@drop.io</email>
			08-24 13:44:36.842: INFO/System.out(1081):   <max_bytes type="integer">104857600</max_bytes>
			08-24 13:44:36.842: INFO/System.out(1081):   <expiration_length>1_WEEK_FROM_LAST_VIEW</expiration_length>
			08-24 13:44:36.842: INFO/System.out(1081):   <name>CarmenDelessioPasswordTest</name>
			08-24 13:44:36.852: INFO/System.out(1081):   <voicemail>646-495-9227 x 82562</voicemail>
			08-24 13:44:36.852: INFO/System.out(1081):   <hidden_upload_url>http://drop.io/hidden/8mjiwvregoat08/upload</hidden_upload_url>
			08-24 13:44:36.852: INFO/System.out(1081): </drop>
		
		*/

		if (localName.equalsIgnoreCase("drop")) {
			// add it to the list
			// myEmpls.add(tempEmp);
		} else if (localName.equalsIgnoreCase("name")) {
			drop.name = currentElementData;
		} else if (localName.equalsIgnoreCase("guests_can_download")) {
			drop.guestCanDownload =currentElementData;
		} else if (localName.equalsIgnoreCase("conference")) {
			drop.conference =currentElementData;
		} else if (localName.equalsIgnoreCase("asset_count")) {
			drop.assetCount =currentElementData;
		} else if (localName.equalsIgnoreCase("current_bytes")) {
			drop.currentBytes =currentElementData;
		} else if (localName.equalsIgnoreCase("guests_can_add")) {
			drop.guestCanAdd =currentElementData;
		} else if (localName.equalsIgnoreCase("rss")) {
			drop.rss =currentElementData;
		} else if (localName.equalsIgnoreCase("fax")) {
			drop.fax =currentElementData;
		} else if (localName.equalsIgnoreCase("guests_can_comment")) {
			drop.guestCanComment =currentElementData;
		} else if (localName.equalsIgnoreCase("guest_token")) {
			drop.guestToken =currentElementData;
		} else if (localName.equalsIgnoreCase("guests_can_delete")) {
			drop.guestCanDelete =currentElementData;
		} else if (localName.equalsIgnoreCase("admin_token")) {
			drop.adminToken =currentElementData;			
		} else if (localName.equalsIgnoreCase("email")) {
			drop.email =currentElementData;
		} else if (localName.equalsIgnoreCase("max_bytes")) {
			drop.maxBytes =currentElementData;
		} else if (localName.equalsIgnoreCase("expiration_length")) {
			drop.expiration =currentElementData;
		} else if (localName.equalsIgnoreCase("voicemail")) {
			drop.voicemail =currentElementData;
		} else if (localName.equalsIgnoreCase("hidden_upload_url")) {
			drop.hiddenUploadUrl =currentElementData;

		}
	}
	
	 public Drop getDrop() {
         return this.drop;
    } 

}
