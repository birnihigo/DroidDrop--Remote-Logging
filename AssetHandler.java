package com.brightkeep.dropionotes;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class AssetHandler extends DefaultHandler {


	private String currentElementData;

	private Asset asset;
	List<Asset> assets = new ArrayList<Asset>();


	public void startElement(String uri, String localName, String qName,
		Attributes attributes) throws SAXException {
		currentElementData = "";
		if (localName.equalsIgnoreCase("asset")) {
			asset = new Asset();
		}

	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {

		currentElementData += new String(ch, start, length);

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.equalsIgnoreCase("asset")) {
			assets.add(asset);
			// add it to the list
			// myEmpls.add(tempEmp);
		} else if (localName.equalsIgnoreCase("name")) {
			asset.name = currentElementData;
		} else if (localName.equalsIgnoreCase("status")) {
			asset.status =currentElementData;
		} else if (localName.equalsIgnoreCase("title")) {
			asset.title =currentElementData;
		} else if (localName.equalsIgnoreCase("hidden_url")) {
			asset.hidden_url =currentElementData;
		} else if (localName.equalsIgnoreCase("created_at")) {
			asset.created_at =currentElementData;
		} else if (localName.equalsIgnoreCase("description")) {
			asset.description =currentElementData;
		} else if (localName.equalsIgnoreCase("contents")) {
			asset.contents =currentElementData;
		} else if (localName.equalsIgnoreCase("name")) {
			asset.name =currentElementData;
		} else if (localName.equalsIgnoreCase("url")) {
			asset.url =currentElementData;
		} else if (localName.equalsIgnoreCase("thumbnail")) {
			asset.thumbnail =currentElementData;
		} else if (localName.equalsIgnoreCase("width")) {
			asset.width =currentElementData;
		} else if (localName.equalsIgnoreCase("height")) {
			asset.height =currentElementData;			
		} else if (localName.equalsIgnoreCase("duration")) {
			asset.duration =currentElementData;
		}
		else if (localName.equalsIgnoreCase("type")) {
			asset.type =currentElementData;
		}
		else if (localName.equalsIgnoreCase("filesize")) {
			asset.filesize =currentElementData;
		}
		else if (localName.equalsIgnoreCase("converted")) {
			asset.converted =currentElementData;
		}
	}
	
	 public Asset getAsset() {
         return this.asset;
    } 
	 
	 public List<Asset> getAssetList() {
         return this.assets;
    } 
	 
	  

}
