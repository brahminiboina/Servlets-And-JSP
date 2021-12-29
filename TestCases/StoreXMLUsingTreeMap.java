package com.wicore.testcases;

import java.io.StringReader;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class StoreXMLUsingTreeMap {
	public static void main(String[] args) throws Exception {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><message id=\"routerSMSNode3@SVHJ0674:84232101352\"><sms type=\"mo\"><retry count=\"0\" max=\"3\"/><destination messageid=\"DCDCB2FA\"><address><number type=\"unknown\">54767</number></address></destination><source><address><number type=\"international\">919669316977</number></address></source><ud type=\"text\">ZM5,SWP01aaa9dac7,1302588,5,1,Wallpaper</ud><vp type=\"absolute\"><date><year>2021</year><month>11</month><day>2</day><hour>18</hour><minute>57</minute><second>3</second><millisecond>739</millisecond></date></vp><scts><date><year>2021</year><month>11</month><day>1</day><hour>18</hour><minute>57</minute><second>3</second><millisecond>738</millisecond></date></scts><param name=\"unique_id\" value=\"71321110200270301130\"/></sms></message>";
        TreeMap<String,String> tm = readXMLToTreeMapFromString(xmlStr,null);
        System.out.println(tm);
        
      /*  String r = "id;number;name;pony";
        for(int i=0;i<r.length();i++) {
        	String p = r.split(";")[i];
        	System.out.println(p);
        } */
    }

	private static TreeMap<String, String> readXMLToTreeMapFromString(String xmlStr, Object logger) {
		String s = xmlStr;
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        String key = "", value = "";
        try {
            treeMap.put("body_payload", s);
            if (s.length() == 0) {
                System.out.print("WATCH-ME-O1:Via:" + logger + "|msg: input stream length is 0:");
                return null;
            }

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(s));
            Document doc = docBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("*");
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);

                key = element.getNodeName();
                value = element.getTextContent();
                if (key.equals("number")) {
                    String type = element.getAttribute("type");
                    if (type.equals("abbreviated"))
                        treeMap.put("shortcode", value);
                    else if (type.equals("national") || type.equals("international"))
                        treeMap.put("msisdn", value);
                } else {
                    treeMap.put(key, value);
                }

                if (key.equals("param")) {
                    String name = element.getAttribute("name");
                    if (name.equals("unique_id")) {
                        String unique_id = element.getAttribute("value");
                        treeMap.put("unique_id", unique_id);
                    }
                }

                System.out.println("key : "+key);
                if (key.equals("message")) {
                    System.out.println("Message...");
                    String nodeId = element.getAttribute("id");
                    String node = nodeId.split("@")[0].split("routerSMSNode")[1];
                    treeMap.put("node", node);

                }

            }

        } catch (org.xml.sax.SAXParseException e) {
            System.out.print("WATCH-ME-O1 | Via:" + logger + "|" + e + "|treeMap=" + treeMap);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.print("WATCH-ME-O1 | Via:" + logger + "|" + e + "|treeMap=" + treeMap);
            e.printStackTrace();
        }
        return treeMap;
    }

}
