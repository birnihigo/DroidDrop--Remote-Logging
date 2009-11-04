package com.brightkeep.dropionotes;


import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import org.apache.http.util.EntityUtils;

import org.apache.http.client.methods.HttpGet;




import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.util.List;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Collection;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;




public class DroidDropNoteClient {
	String apiKey;
	String version="1.0";
	String format="xml";
	String token=null;

	
	// format becomes  api.dropio.com/prefix/dropName/suffix? parm1=1...

	TreeMap<String,String> standardParms=new TreeMap<String,String>();
	HttpResponse restResponse;
	HttpClient restClient;
	String apiServer = "http://api.drop.io";
	String uploadServer = "http://assets.drop.io/upload";
	String callId;
	TreeMap<String,String> dropParms=new TreeMap<String,String>();
	int connectionTimeout=10000;


	public DroidDropNoteClient( ){

		standardParms.put("version", version);
		standardParms.put("format", format);





	}


	public DroidDropNoteClient( String apiKey){
		this();
		this.apiKey=apiKey;
		standardParms.put("api_key", apiKey);

	}

	public DroidDropNoteClient( String apiKey,  String token){
		this(apiKey);
		this.token=token;
		standardParms.put("token", token);
	}

	public DroidDropNoteClient( String apiKey,  String token, String versionParm, String formatParm){
		this(apiKey,token);
		version=versionParm;
		format=formatParm;
		standardParms.put("version", version);
		standardParms.put("format", format);

	}

	


	public  HttpResponse getResponse(boolean doPost, String dropPrefix, String dropName,String dropSuffix, String assetPrefix,String assetName, String assetSuffix, String commentId, TreeMap<String,String> parms)
	throws Exception, ClientProtocolException, UnsupportedEncodingException{
		String currentKey;
		String currentValue;
		String sigParms=""; //String used for creating signature

		StringBuffer ub=new StringBuffer();

		TreeMap<String,String>restParms = new TreeMap<String,String>();
		restParms.putAll(standardParms);
		restParms.putAll(parms);
		Collection<String> c = restParms.keySet();
		Iterator<String> itr = c.iterator();
		if (itr.hasNext()){
			currentKey = (String)itr.next();
			currentValue = restParms.get(currentKey);
			currentValue = URLEncoder.encode(currentValue, "UTF-8");
			sigParms = sigParms +currentKey+"="+currentValue;
			ub.append(sigParms);

		}

		while(itr.hasNext()){
			currentKey = (String)itr.next();
			currentValue = restParms.get(currentKey);
			currentValue = URLEncoder.encode(currentValue, "UTF-8");
			sigParms =  "&"+currentKey+"="+currentValue;
			ub.append(sigParms);
		}
		String postUrl=apiServer;
		if (dropPrefix!=null){
			postUrl+="/"+dropPrefix;
		}
		if (dropName!=null){
			postUrl+="/"+dropName;
		}
		if (dropSuffix!=null){
			postUrl+="/"+dropSuffix;
		}
		if (assetPrefix!=null){
			postUrl+="/"+assetPrefix;
		}
		if (assetName!=null){
			postUrl+="/"+assetName;
		}
		if (assetSuffix!=null){
			postUrl+="/"+assetSuffix;
		}
		if (commentId!=null){
			postUrl+="/"+commentId;
		}
		postUrl+="/";

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams,
				connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParams,
				connectionTimeout);
		restClient = new DefaultHttpClient(httpParams);
		
		HttpPost post = new HttpPost(postUrl+"?"+ub.toString());
		HttpGet get = new HttpGet(postUrl+"?"+ub.toString());

		if (doPost){
			restResponse =	 restClient.execute(post);
		}else{
			restResponse =	 restClient.execute(get);
		}
		return(restResponse);

	}

	public String call(boolean doPost, String prefix, String dropName,String suffix, String assetPrefix, String assetName, String assetSuffix, String commentId, TreeMap<String,String> parms)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		HttpResponse thisResponse;
		String errorReturn;
		String errorMessage;

		thisResponse = this.getResponse(doPost, prefix,dropName,suffix,assetPrefix, assetName,assetSuffix, commentId,  parms);
		if (thisResponse==null){
			throw new Exception("No Response");

		}


		int status= restResponse.getStatusLine().getStatusCode();
		switch (status){

		case 400: 
		case 403: 
		case 404: 
		case 500: 
			errorReturn = EntityUtils.toString(thisResponse.getEntity());
			errorMessage = getErrorMsg(errorReturn);
			if (errorMessage == null) errorMessage = "Server response was "+status;			
			throw new Exception(errorMessage);

		case 200: return EntityUtils.toString(thisResponse.getEntity());
		default: errorMessage = "Server response was "+status;			
		throw new Exception(errorMessage);

		}

	}




	public String getDrop(String dropName, String password)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		dropParms.clear();
		if (password !=null){
			dropParms.put("token", password);
		}
		return (this.call(false, "drops",dropName,null,null,null,null,null,dropParms));
	}
	
	public String getDrop(String dropName)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		dropParms.clear();
		if (dropName ==null) throw new Exception("Drop Name required to get Drop");
		return (this.call(false, "drops",dropName,null,null,null,null,null,dropParms));
	}
	
	public String getAssetList(String dropName, String page)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{  // review, need paging
		dropParms.clear();
		if (dropName ==null) throw new Exception("Drop Name required to get Asset List");
		if (page !=null){
			dropParms.put("page", page);
		}
		return (this.call(false, "drops",dropName,"assets",null, null,null,null,dropParms));
	}
	
	public String getAsset(String dropName, String assetName)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		dropParms.clear();
		if (dropName ==null) throw new Exception("Drop Name required to get Asset");
		if (assetName ==null) throw new Exception("Asset Name required to get Asset");

		return (this.call(false, "drops",dropName,"assets",assetName,null,null,null,dropParms));
	}
	
	public String getAssetEmbed(String dropName, String assetName)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		dropParms.clear();
		return (this.call(false, "drops",dropName,"assets",assetName,"embed_code",null,null,dropParms));
	}
	public String createNote(String dropName, String title, String contents)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		dropParms.clear();
		//dropParms.put("name", dropName);
		if (title!=null)
			dropParms.put("title", title);
		if (contents==null)
			throw new Exception ("Content required when creating a note");
		else{
			dropParms.put("contents", contents); //required
			return (this.call(true, "drops",dropName, "assets",null,null,null,null,dropParms));
		}
	}
	
	public String createLink(String dropName, String title, String description, String url)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
		dropParms.clear();
		if (title!=null)
			dropParms.put("title", title);
		if (description!=null)
			dropParms.put("description", description);

		if (url==null)
			throw new Exception ("URL required when creating a link");
		else{
			dropParms.put("url", url); //required
			return (this.call(true, "drops",dropName, "assets",null,null,null,null,dropParms));
		}

	}
	

	
	
	public String createDrop(String dropName, String guestAdd, String guestDelete, String guestComment, String expiration, String guestPassword, String adminPassword)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
	dropParms.clear();
	if (dropName!=null){
		dropParms.put("name",dropName );
	}
	if (guestComment!=null){
	dropParms.put("guests_can_comment", guestComment );
	}
	if (guestAdd!=null){
	dropParms.put("guests_can_add", guestAdd );
	}
	if (guestDelete!=null){
	dropParms.put("guests_can_delete", guestDelete );
	}
	if (guestPassword!=null){
		dropParms.put("password",guestPassword );
	}
	if (adminPassword!=null){
		dropParms.put("admin_password",adminPassword );
	}
	
	if (expiration!=null){
		dropParms.put("expiration_length",expiration );
	}

	return (this.call(true, "drops",null,null,null,null,null,null,dropParms));

	}
	
	public String updateDrop(String dropName, String guestAdd, String guestDelete, String guestComment, String expiration, String guestPassword, String adminPassword)
	throws Exception, ClientProtocolException, UnsupportedEncodingException, ParseException{
	dropParms.clear();
	if (dropName==null){
		throw new Exception ("Drop name required when updating a drop");
	}

	if (guestComment!=null){
	dropParms.put("guests_can_comment", guestComment );
	}
	if (guestAdd!=null){
	dropParms.put("guests_can_add", guestAdd );
	}
	if (guestDelete!=null){
	dropParms.put("guests_can_delete", guestDelete );
	}
	if (guestPassword!=null){
		dropParms.put("password",guestPassword );
	}
	if (adminPassword!=null){
		dropParms.put("admin_password",adminPassword );
	}
	
	if (expiration!=null){
		dropParms.put("expiration_length",expiration );
	}

	return (this.call(true, "drops",dropName,null,null,null,null,null,dropParms));

	}
	

	
	public void setRequestParms(TreeMap<String,String> parms){
		TreeMap<String,String>requestParms = new TreeMap<String,String>();
		requestParms.putAll(standardParms);
		requestParms.putAll(parms);
	}

	public void setToken(String token){
		this.token=token;
		standardParms.put("token", token);
	}

	public String getToken(){
		return(this.token);
	}
	
	public void setFormat(String formatParm){
		this.format=formatParm;
		standardParms.put("format", format);

	}

	public void setVersion(String versionParm){
		this.version=versionParm;
		standardParms.put("version", version);

	}

	public void setApiKey(String apiKey){
		this.apiKey=apiKey;
		standardParms.put("api_key", apiKey);

	}

	public String getApiKey(){
		return(this.apiKey);
	}





	

	
	public String getErrorMsg(String response, String format){
		if (format.equals("XML")){
			return getErrorMsg(response);
		}
		try {
			JSONObject error = new JSONObject(response);
			return error.getString("error_msg");
		} catch (JSONException e) {
			return null;
		}

	}
	
	// this works for XML
	public String getErrorMsg(String response){
		String sTag = "<message>";
		String eTag = "</message>";

		int c1 = response.indexOf(sTag);
		int c2 = response.indexOf(eTag);
		if (c1 != -1 && c2 != -1) {
			return(response.substring(c1 + sTag.length(), c2));
		}
		return null;
	}
	// might need this later
	public String getErrorFormat(String response){
		int offset=-1;
		offset=response.indexOf("xml version");
		if (offset==-1){
			return "JSON";
		}
		return "XML";
		
	}
	public static Drop getDropfromString(String dropData) throws Exception{
		// Use SAX to parse the drop
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		DropHandler dropHandler = new DropHandler();
		xr.setContentHandler(dropHandler);
		InputSource s = new InputSource(new StringReader(dropData)); 
		xr.parse(s);
		
		//Drop created
		Drop createdDrop=dropHandler.getDrop(); 
		if (createdDrop==null) throw new Exception ("Drop is null");
		return (createdDrop);
	}
	
	public static Asset getAssetfromString(String assetData) throws Exception{
		// Use SAX to parse the drop
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		AssetHandler assetHandler = new AssetHandler();
		xr.setContentHandler(assetHandler);
		InputSource s = new InputSource(new StringReader(assetData)); 
		xr.parse(s);
		
		//Drop created
		Asset asset=assetHandler.getAsset(); 
		if (asset==null) throw new Exception ("Asset is null");
		return (asset);
	}

	
	public static List<Asset> getAssetListfromString(String assetData) throws Exception{
		// Use SAX to parse the drop
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		AssetHandler assetHandler = new AssetHandler();
		xr.setContentHandler(assetHandler);
		InputSource s = new InputSource(new StringReader(assetData)); 
		xr.parse(s);
		
		//Drop created
		List<Asset> assets=assetHandler.getAssetList(); 
		if (assets==null) throw new Exception ("Asset is null");
		return (assets);
	}



}
