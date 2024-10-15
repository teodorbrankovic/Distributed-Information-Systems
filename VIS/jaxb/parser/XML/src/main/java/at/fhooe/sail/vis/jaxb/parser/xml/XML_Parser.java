package at.fhooe.sail.vis.jaxb.parser.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.StringReader;

public class XML_Parser {

    static class WindHandler extends DefaultHandler {
        private Wind wind;
        private StringBuilder elementValue;

        @Override
        public void startElement(String _uri, String _localName, String _qName, Attributes _atts) throws SAXException {
            if ("wind".equals(_qName)) {
                wind = new Wind(0, 0);
            }
            elementValue = new StringBuilder();
        }

        @Override
        public void endElement(String _uri, String _localName, String _qName) throws SAXException {
            switch (_qName) {
                case "speed":
                    wind.setSpeed(Double.parseDouble(elementValue.toString()));
                    break;
                case "deg":
                    wind.setDegree(Integer.parseInt(elementValue.toString()));
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            elementValue.append(ch, start, length);
        }

        public Wind getWind() {
            return wind;
        }

    } // end WindHandler


    public static void main(String[] args) {

        try {
            String data = "<wind>\n" +
                    "<speed>50.25</speed>\n" +
                    "<deg>225</deg>\n" +
                    "</wind>";
            String name = "data.xml";
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xmlReader = sp.getXMLReader();


            WindHandler handler = new WindHandler();
            xmlReader.setContentHandler(handler);


            // data is a String containing the XML data, so use a StringReader
            xmlReader.parse(new InputSource(new StringReader(data)));


            // get the Wind object from the handler
            Wind wind = handler.getWind();
            System.out.println();
            System.out.println("Task 3.1.b");
            System.out.println();
            System.out.println("----parser/XML----");
            System.out.println(wind);

        } catch (Exception _e) {
            _e.printStackTrace();
        }

    }
}