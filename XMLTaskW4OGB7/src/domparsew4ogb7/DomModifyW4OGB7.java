package domparsew4ogb7;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyW4OGB7 {
    public static void main(String[] args) {
        String filePath = "XMLW4OGB7.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Módosítja a bolt székhelyének címét
            updateElementValue(doc);

            // Törli a munkavállaló címét
            deleteElement(doc);

            // Hozzáad egy "tipus" elemet a termékhez
            addElement(doc);

            // Kiírja egy fileba a módosításokat
            writeXMLFile(doc);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    private static void writeXMLFile(Document doc)
    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("Updated_XMLW4OGB7.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML módosítása sikeres volt");
    }

     //Elem hozzáadása a termékhez.
    private static void addElement(Document doc) {
    	NodeList bolt = doc.getElementsByTagName("termek");
        Element termek = null;
        
        // loop for each user
        for (int i = 0; i < bolt.getLength(); i++) {
            termek = (Element) bolt.item(i); 
            Element id = doc.createElement("tipus");
            id.appendChild(doc.createTextNode("PC Alkatrészek"));
            termek.appendChild(id);
        }
    }

    //Egy elem törlése
    private static void deleteElement(Document doc) {
    	NodeList bolt = doc.getElementsByTagName("munkavallalo");
        Element munkavallalo = null;
        // loop for each user
        for (int i = 0; i < bolt.getLength(); i++) {
            munkavallalo = (Element) bolt.item(i);
            Node cim = munkavallalo.getElementsByTagName("cim").item(0);
            munkavallalo.removeChild(cim);
        }
    }

    //Első elem frissítése az XML doksiban.
    private static void updateElementValue(Document doc) {
        NodeList bolt = doc.getElementsByTagName("boltadatok");
        Element boltadatok = null;
        // loop for each user
        for (int i = 0; i < bolt.getLength(); i++) {
            boltadatok = (Element) bolt.item(i);
            Node szekhely = boltadatok.getElementsByTagName("szekhely").item(0).getFirstChild();
            szekhely.setNodeValue("5879 Magyaralföld, Dózsa u 63.");
        }
    }
}