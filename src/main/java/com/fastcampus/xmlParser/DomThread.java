package com.fastcampus.xmlParser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomThread extends Thread {

    // 다운로드 받은 문자열을 저장할 변수
    private String xml;
    // 파싱한 결과를 저장할 리스트 - 몇개인지 모르므로 배열이 아니고 ArrayList로 만들어야 합니다.
    List<WeatherData> weatherDataList = new ArrayList<>();
    //List<String> data = new ArrayList<String>();

    public void run() {

        try {
            // 연결+옵션설정
            String addr = "http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109";
            URL url = new URL(addr);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setConnectTimeout(10000);
            http.setUseCaches(false);

            // 위 부분 까지는 주소만 변경되고 모든 경우 동일

            // 위 주소에서 주는 데이터를 문자열로 읽기 위한 스트림 객체 생성
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));

            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                sb.append(line);
            }
            xml = sb.toString();
            br.close();
            http.disconnect();

        } catch (Exception e) {
            System.out.println("다운로드에러" + e.getMessage());

        }
        // System.out.println(xml);

        // 파싱
//        try {
//            // 자신의 static 메서드를 가지고 객체를 생성 : 싱글톤 패턴
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            // 다른 클래스의 객체를 가지고, 객체를 생성하면 팩토리 패턴.
//            DocumentBuilder documentbuilder = factory.newDocumentBuilder(); //// 팩토리 메서드 패턴  공장에서 찍어줌
//            // 문자열을 InputStream으로 변환
//            InputStream is = new ByteArrayInputStream(xml.getBytes());
//
//            Document doc = documentbuilder.parse(is);
//            // xml을 메모리에 펼쳐놓고 루트를 elemnt에 저장
//            Element element = doc.getDocumentElement();
//
//            // 파싱할 태그의 리스트를 찾아온다
//            // tmx 태그 전체를 list에 저장
//            NodeList list = element.getElementsByTagName("wf");
//            // 리스트를 순회하면서 데이터를 data에 추가
//            for (int i = 0; i < list.getLength(); i++) {
//                // i번째 tmx 태그를 node에 저장
//                Node node = list.item(i);
//                // 태그 내의 첫번째 값 앞으로 이동
//                Node temp = node.getFirstChild();
//                // 태그 내의 첫번째 값을 value에 저장
//                String value = temp.getNodeValue();
//                // 값을 data에 저장
//                data.add(value);
//
//            }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xml.getBytes());
            Document doc = documentBuilder.parse(is);
            Element element = doc.getDocumentElement();

            // <data> 태그의 리스트를 찾아온다
            NodeList dataList = element.getElementsByTagName("data");
            for (int i = 0; i < dataList.getLength(); i++) {
                System.out.println("dataList.getLength() = " + dataList.getLength());//dataList의 길이
                Element dataElement = (Element) dataList.item(i);

                String numEf = dataElement.getElementsByTagName("numEf").item(0).getTextContent(); //데이터추출 : dataElement에서 특정 태그 이름을 가진 하위 요소들을 찾아내어 그 값을 읽는다.
                String tmEf = dataElement.getElementsByTagName("tmEf").item(0).getTextContent();
                String wf = dataElement.getElementsByTagName("wf").item(0).getTextContent();
                String tmn = dataElement.getElementsByTagName("tmn").item(0).getTextContent();
                String tmx = dataElement.getElementsByTagName("tmx").item(0).getTextContent();

                WeatherData wd = new WeatherData(numEf, tmEf, wf, tmn, tmx);
                weatherDataList.add(wd); //xml파일안에 데이터를 이 리스트에 저장
            }

        } catch (Exception e) {
            System.out.println("파싱에러" + e.getMessage());
        }

        // data의 내용을 출력 - 단순 확인만 하는 경우
        // 컬렉션의 toString은 각 데이터의 toString을 다시 호출

//        for(String imsi : data){
//            System.out.println(imsi);
        for (WeatherData wd : weatherDataList) {
            System.out.println(wd);
        }
    }
}