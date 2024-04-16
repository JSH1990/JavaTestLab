package com.fastcampus.xmlParser2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Parser {
    public static void main(String[] args) {


    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;

    {
        try {
            /* DocumentBuilder 클래스는 XML 문서에서 DOM Document instance를 얻는다.
            DocumentBuilder 클래스를 사용하면 XML로부터 Document를 얻을 수 있다.
            DocumentBuilder의 인스턴스를 할당하기 위해서는 DocumentBuilderFactory.newDocumentBuilder() 메소드를 사용해야한다.
            이름에서부터 나와있듯이 팩토리 패턴을 사용한다.
            다른 클래스의 인스턴스를 가지고 객체를 생성하면 팩토리 패턴이다.*/

            builder = factory.newDocumentBuilder(); //DocumentBuilderFactory 클래스의 인스턴스 생성해, 메서드를 호출해 builder에 넣는다.

            Document document = builder.parse("src/main/resources/sample.xml");

            //root 요소 가져오기
            Element root = document.getDocumentElement();
            //root 요소 확인 : 첫 태그의 sample태그 가져온다.
            System.out.println("root.getNodeName() = " + root.getNodeName());

            //root 요소의 첫번째 노드는 #Text
            /*
             <root> 태그 바로 다음에는 줄바꿈과 들여쓰기가 있다. 이 공백은 DOM에서 #Text 노드로 처리된다.
             따라서, <root> 요소의 첫 번째 자식으로 #Text 노드가 등장하고, 그 다음이 실제 요소인 <customer>가 된다.
             */
            Node firstNode = root.getFirstChild();
            System.out.println("firstNode.getNodeName() = " + firstNode.getNodeName());

            //다음 노드는 customer
            Node customer = firstNode.getNextSibling();
            //customer 요소안의 노드리스트
            NodeList childList = customer.getChildNodes();

            for (int i=0; i<childList.getLength(); i++){
                Node item = childList.item(i);
                if(item.getNodeType() == Node.ELEMENT_NODE){ //노드의 타입이 Element일 경우(공백이 아닌경우)
                    System.out.println("item.getNodeName() = " + item.getNodeName());
                    System.out.println("item.getTextContent() = " + item.getTextContent());
                }else {
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("파싱에러입니다 = " + e.getMessage());
        }
    }
    }
}
