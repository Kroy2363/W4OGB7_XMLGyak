package domparsew4ogb7;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadW4OGB7 {

	public static void main(String[] args) {
		try{
			File xmlDoc = new File("XMLW4OGB7.xml");
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = dBuild.parse(xmlDoc);

			//root element
			System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
			
			//read bolt element
			NodeList nList = doc.getElementsByTagName("bolt");
			for(int i=0; i<nList.getLength();i++)
			{
				Node nNode = nList.item(i);
				System.out.print("Node name:" + nNode.getNodeName() + " " + (i+1));
				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode; 
					//Bolt adatai
					System.out.println("bolt Id #: " + eElement.getAttribute("idno"));
					System.out.println("Bolt székhelye: " + 
						eElement.getElementsByTagName("szekhely").item(0).getTextContent());
					System.out.println("Bolt telefonszáma: " + 
						eElement.getElementsByTagName("telefonszam").item(0).getTextContent());
					System.out.println("Bolt email címe: " + 
						eElement.getElementsByTagName("email").item(0).getTextContent());
					//Munkavállaló adatai
					System.out.println("Munkavállaló adatai: " + "\n"+"Neve:"+
						eElement.getElementsByTagName("v.nev").item(0).getTextContent()+" "+eElement.getElementsByTagName("k.nev").item(0).getTextContent()
						+"\n"+"Munkavállaló címe: "+eElement.getElementsByTagName("varos").item(0).getTextContent()+
						eElement.getElementsByTagName("utcahazszam").item(0).getTextContent()+"\n"+"Születési ideje:"+eElement.getElementsByTagName("szulido").item(0).getTextContent()+"\n"+
						"Jogosultsága: "+eElement.getElementsByTagName("jogosultsag").item(0).getTextContent()
						);
					//Termék adatai
					System.out.println("Termék adatai: " + "\n"+"Elnevezés:"+
							eElement.getElementsByTagName("elnevezes").item(0).getTextContent()+"\n"+"Cikkszám:"+eElement.getElementsByTagName("cikkszam").item(0).getTextContent()
							+"\n"+"Ár: "+eElement.getElementsByTagName("ar").item(0).getTextContent()+" Ft \n"+"Raktárkészlet: "+eElement.getElementsByTagName("darab").item(0).getTextContent()+" db");
					//Ügyfél adatai
					System.out.println("Ügyfél adatai: " + "\n"+"Neve:"+
							eElement.getElementsByTagName("ugyfel").item(0).getTextContent());

				}
			}
			
			
			
		}		
		catch(Exception e){}

	}

}
