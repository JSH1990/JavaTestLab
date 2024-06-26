package com.fastcampus.xmlParser3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMParser {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //XML 문서 파싱
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/main/resources/sample2.xml");

        //root 구하기
        Element root = document.getDocumentElement();

        //root 의 속성
        System.out.println("class name = " + root.getAttribute("name"));

        NodeList childeren = root.getChildNodes(); //자식 노드 목록 get
        for(int i=0; i< childeren.getLength(); i++){
            Node node = childeren.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){ //해당 노드의 판정(Element일때)
                Element ele = (Element) node;
                String nodeName = ele.getNodeName();
                System.out.println("node name = " + nodeName);
                if(nodeName.equals("teacher")){
                    System.out.println("node attribute: " + ele.getAttribute("name"));
                }
                else if(nodeName.equals("student")){
                    //이름이 student인 노드는 자식노드가 더 존재함
                    NodeList children2 = ele.getChildNodes();
                    for(int a = 0; a < children2.getLength(); a++){
                        Node node2 = children2.item(a);
                        if(node2.getNodeType() == Node.ELEMENT_NODE){
                            Element ele2 = (Element)node2;
                            String nodeName2 = ele2.getNodeName();
                            System.out.println("node name2 = " + nodeName2);
                            System.out.println("node attribute2 = " + ele2.getAttribute("num"));
                        }
                    }
                }
            }
        }
    }
}
