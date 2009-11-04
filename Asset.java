package com.brightkeep.dropionotes;
import java.util.ArrayList;
import java.util.List;


public class Asset extends Object{
	String type;
	String status;
	String fileSize;
	String title;
	String hidden_url;
	String created_at;
	String description;
	String contents;
	String name;	
	String url;
	String thumbnail;
	String width;
	String height;
	String duration;
	String filesize;
	String converted;

	/*
	08-27 22:21:00.582: INFO/System.out(12462):   <type>note</type>
		08-27 22:21:00.582: INFO/System.out(12462):   <status>converted</status>
		08-27 22:21:00.582: INFO/System.out(12462):   <filesize type="integer">43</filesize>
		08-27 22:21:00.582: INFO/System.out(12462):   <title>1st Test Note</title>
		08-27 22:21:00.582: INFO/System.out(12462):   <hidden_url>http://drop.io/hidden/j2cfm1loutbuvw/asset/MXN0LXRlc3Qtbm90ZQ==</hidden_url>
		08-27 22:21:00.582: INFO/System.out(12462):   <created_at>2009-08-19 21:06:22 UTC</created_at>
		08-27 22:21:00.582: INFO/System.out(12462):   <description nil="true"></description>
		08-27 22:21:00.582: INFO/System.out(12462):   <contents>This is the contents of the first test note</contents>
		08-27 22:21:00.582: INFO/System.out(12462):   <name>1st-test-note</name>
		08-27 22:21:00.582: INFO/System.out(12462): </asset>
		
			08-27 22:21:00.602: INFO/System.out(12462):   <type>link</type>
		08-27 22:21:00.602: INFO/System.out(12462):   <status>converted</status>
		08-27 22:21:00.602: INFO/System.out(12462):   <filesize type="integer">0</filesize>
		08-27 22:21:00.602: INFO/System.out(12462):   <title>Link to talkingandroid.com</title>
		08-27 22:21:00.602: INFO/System.out(12462):   <hidden_url>http://drop.io/hidden/j2cfm1loutbuvw/asset/bGluay10by10YWxraW5nYW5kcm9pZC1jb20=</hidden_url>
		08-27 22:21:00.602: INFO/System.out(12462):   <created_at>2009-08-20 14:17:50 UTC</created_at>
		08-27 22:21:00.602: INFO/System.out(12462):   <description>Talkingandroid.com is my blog for Android work</description>
		08-27 22:21:00.602: INFO/System.out(12462):   <url>http://talkingandroid.com</url>
		08-27 22:21:00.602: INFO/System.out(12462):   <name>link-to-talkingandroid-com</name>
		08-27 22:21:00.602: INFO/System.out(12462): </asset>
		
		08-27 22:21:00.602: INFO/System.out(12462):   <type>image</type>
		08-27 22:21:00.602: INFO/System.out(12462):   <status>converted</status>
		08-27 22:21:00.602: INFO/System.out(12462):   <thumbnail>http://drop.io/download/public/g6aovihqcxdawjcw8rld/a588ca2e627312c3e6ae0279ea31bb4a4b0b35b6/a68da580-6f23-012c-8853-f898da0481c8/3971cac0-6fc3-012c-83cf-f9fa6e77c577/v2/thumbnail</thumbnail>
		08-27 22:21:00.602: INFO/System.out(12462):   <filesize type="integer">734252</filesize>
		08-27 22:21:00.602: INFO/System.out(12462):   <title>IMG_0408.jpg</title>
		08-27 22:21:00.602: INFO/System.out(12462):   <hidden_url>http://drop.io/hidden/j2cfm1loutbuvw/asset/aW1nLTA0MDgtanBn</hidden_url>
		08-27 22:21:00.602: INFO/System.out(12462):   <created_at>2009-08-20 14:25:25 UTC</created_at>
		08-27 22:21:00.602: INFO/System.out(12462):   <description nil="true"></description>
		08-27 22:21:00.602: INFO/System.out(12462):   <converted>http://drop.io/download/public/g6aovihqcxdawjcw8rld/e0f9e61fcbd32d04c3658cf7977079d46af1a248/a68da580-6f23-012c-8853-f898da0481c8/3971cac0-6fc3-012c-83cf-f9fa6e77c577/v2/thumbnail_large</converted>
		08-27 22:21:00.602: INFO/System.out(12462):   <width type="integer">1200</width>
		08-27 22:21:00.602: INFO/System.out(12462):   <name>img-0408-jpg</name>
		08-27 22:21:00.602: INFO/System.out(12462):   <height type="integer">1600</height>
		08-27 22:21:00.602: INFO/System.out(12462): </asset>
		08-27 22:21:00.602: INFO
		
		08-27 22:21:00.602: INFO/System.out(12462): <asset>
		08-27 22:21:00.602: INFO/System.out(12462):   <type>movie</type>
		08-27 22:21:00.602: INFO/System.out(12462):   <status>converted</status>
		08-27 22:21:00.602: INFO/System.out(12462):   <thumbnail>http://drop.io/download/public/g6aovihqcxdawjcw8rld/40e483fe16eeadb33e080c7f9ea174d2bdb964fa/a68da580-6f23-012c-8853-f898da0481c8/f3142af0-6fc4-012c-42f2-f50f7a0a9fbc/v2/thumbnail</thumbnail>
		08-27 22:21:00.602: INFO/System.out(12462):   <filesize type="integer">1903209</filesize>
		08-27 22:21:00.602: INFO/System.out(12462):   <title>ISUHD18.mov</title>
		08-27 22:21:00.602: INFO/System.out(12462):   <hidden_url>http://drop.io/hidden/j2cfm1loutbuvw/asset/aXN1aGQxOC1tb3Y=</hidden_url>
		08-27 22:21:00.602: INFO/System.out(12462):   <created_at>2009-08-20 14:37:46 UTC</created_at>
		08-27 22:21:00.602: INFO/System.out(12462):   <description nil="true"></description>
		08-27 22:21:00.602: INFO/System.out(12462):   <converted>http://drop.io/download/public/g6aovihqcxdawjcw8rld/860b81d275d653d52c0704a5cea756b008668e74/a68da580-6f23-012c-8853-f898da0481c8/f3142af0-6fc4-012c-42f2-f50f7a0a9fbc/v2/content</converted>
		08-27 22:21:00.602: INFO/System.out(12462):   <name>isuhd18-mov</name>
		08-27 22:21:00.602: INFO/System.out(12462):   <duration type="integer">62</duration>
		08-27 22:21:00.602: INFO/System.out(12462): </asset>
		08-27 22:21:00.602: INFO/System.out(12462): <asset>
		08-27 22:21:00.602: INFO/System.out(12462):   <type>other</type>
		08-27 22:21:00.602: INFO/System.out(12462):   <status>converted</status>
		08-27 22:21:00.602: INFO/System.out(12462):   <filesize type="integer">111548</filesize>
		08-27 22:21:00.602: INFO/System.out(12462):   <title>video-2009-08-18-14-47-05.3gp</title>
		08-27 22:21:00.602: INFO/System.out(12462):   <hidden_url>http://drop.io/hidden/j2cfm1loutbuvw/asset/dmlkZW8tMjAwOS0wOC0xOC0xNC00Ny0wNS0zZ3A=</hidden_url>
		08-27 22:21:00.602: INFO/System.out(12462):   <created_at>2009-08-20 14:49:36 UTC</created_at>
		08-27 22:21:00.602: INFO/System.out(12462):   <description nil="true"></description>
		08-27 22:21:00.602: INFO/System.out(12462):   <name>video-2009-08-18-14-47-05-3gp</name>
		08-27 22:21:00.602: INFO/System.out(12462):   <duration type="integer">5</duration>
		08-27 22:21:00.602: INFO/System.out(12462): </asset>
		08-27 22:21:00.602: INF
		
		08-24 16:28:32.625: INFO/System.out(1693): <asset>
08-24 16:28:32.632: INFO/System.out(1693):   <type>other</type>
08-24 16:28:32.632: INFO/System.out(1693):   <status>unconverted</status>
08-24 16:28:32.632: INFO/System.out(1693):   <title>bytearray</title>
08-24 16:28:32.632: INFO/System.out(1693):   <description nil="true"></description>
08-24 16:28:32.632: INFO/System.out(1693):   <created_at>2009-08-24 20:28:40 UTC</created_at>
08-24 16:28:32.632: INFO/System.out(1693):   <hidden_url>http://drop.io/hidden/j2cfm1loutbuvw/asset/Ynl0ZWFycmF5</hidden_url>
08-24 16:28:32.642: INFO/System.out(1693):   <filesize type="integer">76</filesize>
08-24 16:28:32.642: INFO/System.out(1693):   <name>bytearray</name>
08-24 16:28:32.642: INFO/System.out(1693): </asset>
	
	*/

	
	public Asset(){

	}
	
	public Asset(String name){
		this.name=name;
	}
	

	
	public String getName(){
		return(name);
	}	
	public void setName(String name){
		this.name=name;
	}
	

	
}
