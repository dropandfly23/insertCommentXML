
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) throws Exception {

        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<ct:APC xmlns:ct=\"http://canaltrain.sncf.fr/train-communication\"\n"
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n"
                + "\txsi:schemaLocation=\"http://canaltrain.sncf.fr/train-communication apc-formalisme-sncf-v0.2.xsd\"\n" + "\tschemaVer=\"1.03\" \n"
                + "\tid=\"Z51501_UGC_APC_2010-08-31T09:00:00_1.xml\">\n"
                + "\t<ct:Header ConsistID=\"Z51501\" FileDate=\"2010-08-31T09:00:00\" Ver=\"1.0\" Counter=\"34562\">\n"
                + "\t\t<ct:GPS val=\"1\" lat=\"40.56453\" lon=\"1.3333\" dir=\"12.1\" alt=\"12\" Unit=\"9\" vit=\"25\"/>\n" + "\t</ct:Header>\n"
                + "\t<ct:DUnits dNb=\"3\" myDPos=\"1\" iTs=\"2010-08-31T10:35:00\" >\n"
                + "\t\t<ct:DUnit dPos=\"1\" ID=\"Z51501\" leadVeh=\"Z515018\" orientation=\"2\"/>\n"
                + "\t\t<ct:DUnit dPos=\"2\" ID=\"Z51510\" leadVeh=\"Z515101\" orientation=\"1\"/>\n"
                + "\t\t<ct:DUnit dPos=\"3\" ID=\"Z51511\" leadVeh=\"Z515118\" orientation=\"2\"/>\n" + "\t</ct:DUnits>\n"
                + "\t<ct:Stop Id=\"1\" ChargeRT=\"32\" ArrivalT=\"2010-08-31T10:35:00\" DepartureT=\"2010-08-31T10:37:00\" DoorRelease_R=\"0\" DoorRelease_L=\"1\" Duration=\"120\">\n"
                + "\t\t<ct:Pis SiveId=\"ME2342\" StationId=\"LMN\" StationType=\"2\" ThTime=\"10:35:00\" DepStation=\"PAR\" ArrStation=\"NAN\" Status=\"1\" ModeT=\"1\"/>\n"
                + "\t\t<ct:GPS val=\"1\" lat=\"40.56453\" lon=\"1.4444\" dir=\"12.1\" Unit=\"8\"/>\n" + "\t\t\n"
                + "\t\t<ct:Doors TotalIn=\"56\" TotalOut=\"84\" ExchangeT=\"91\">\n"
                + "\t\t\t<ct:Door Id=\"1\" In=\"0\" Out=\"0\" Unk=\"0\" VoitId=\"ZR1\" Open=\"11\" />\n"
                + "\t\t\t<ct:Door Id=\"2\" In=\"0\" Out=\"0\" Unk=\"0\" VoitId=\"ZR1\" Open=\"15\" />\n"
                + "\t\t\t<ct:Door Id=\"3\" In=\"0\" Out=\"0\" Unk=\"0\" VoitId=\"ZR2\" Open=\"7\" />\n"
                + "\t\t\t<ct:Door Id=\"4\" In=\"0\" Out=\"0\" Unk=\"0\" VoitId=\"ZR2\" Open=\"8\" />\n"
                + "\t\t\t<ct:Door Id=\"5\" In=\"23\" Out=\"11\" Unk=\"1\" VoitId=\"ZR3\" Open=\"5\" Err=\"1\" ErrSensor=\"1\"/>\n"
                + "\t\t\t<ct:Door Id=\"6\" In=\"03\" Out=\"1\" Unk=\"0\" VoitId=\"ZR3\" Open=\"6\" />\n"
                + "\t\t\t<ct:Door Id=\"7\" In=\"21\" Out=\"41\" Unk=\"1\" VoitId=\"ZR4\" Open=\"14\" />\n"
                + "\t\t\t<ct:Door Id=\"8\" In=\"9\" Out=\"31\" Unk=\"1\" VoitId=\"ZR4\" Open=\"11\" />\n"
                + "\t\t\t<ct:DInt Id=\"1\" S1=\"3\" S2=\"1\" VoitId=\"ZR1\" />\n" + "\t\t\t<ct:DInt Id=\"2\" S1=\"0\" S2=\"4\" VoitId=\"ZR2\" />\n"
                + "\t\t\t<ct:DInt Id=\"3\" S1=\"3\" S2=\"5\" VoitId=\"ZR3\" />\n" + "\t\t</ct:Doors>\n" + "\t</ct:Stop>\n"
                + "\t<ct:Stop Id=\"2\" ChargeRT=\"32\" ArrivalT=\"2010-08-31T11:06:00\" DepartureT=\"2010-08-31T11:09:00\" DoorRelease_R=\"0\" DoorRelease_L=\"1\" Duration=\"180\">\n"
                + "\t\t<ct:Pis SiveId=\"ME2342\" StationId=\"ANG\" StationType=\"2\" ThTime=\"11:05:00\" DepStation=\"PAR\" ArrStation=\"NAN\" Status=\"1\" ModeT=\"1\"/>\n"
                + "\t\t<ct:GPS val=\"1\" lat=\"40.56453\" lon=\"1.5555\" dir=\"45.2\" Unit=\"6\"/>\n"
                + "\t\t<ct:Doors TotalIn=\"49\" TotalOut=\"75\" ExchangeT=\"132\" >\n"
                + "\t\t\t<ct:Door Id=\"1\" In=\"23\" Out=\"61\" Unk=\"1\" VoitId=\"ZR1\" Err=\"1\" ErrSensor=\"1\"/>\n"
                + "\t\t\t<ct:Door Id=\"2\" In=\"03\" Out=\"1\" VoitId=\"ZR1\" />\n"
                + "\t\t\t<ct:Door Id=\"3\" In=\"21\" Out=\"4\" Unk=\"1\" VoitId=\"ZR2\" Err=\"1\" ErrSensor=\"1\"/>\n"
                + "\t\t\t<ct:Door Id=\"4\" In=\"2\" Out=\"9\" Unk=\"1\" VoitId=\"ZR2\" Open=\"9\" />\n" + "\t\t\t<ct:Door Id=\"5\" In=\"0\" Out=\"0\" VoitId=\"ZR3\" />\n"
                + "\t\t\t<ct:Door Id=\"6\" In=\"0\" Out=\"0\" VoitId=\"ZR3\" />\n" + "\t\t\t<ct:Door Id=\"7\" In=\"0\" Out=\"0\" VoitId=\"ZR4\" />\n"
                + "\t\t\t<ct:Door Id=\"8\" In=\"0\" Out=\"0\" VoitId=\"ZR4\" />\n" + "\t\t\t<ct:DInt Id=\"1\" S1=\"3\" S2=\"1\" VoitId=\"ZR1\" />\n"
                + "\t\t\t<ct:DInt Id=\"2\" S1=\"0\" S2=\"4\" VoitId=\"ZR2\" />\n" + "\t\t\t<ct:DInt Id=\"3\" S1=\"3\" S2=\"5\" VoitId=\"ZR3\" />\n" + "\t\t</ct:Doors>\n"
                + "\t</ct:Stop>\n" + "\t<ct:DUnits dNb=\"2\" myDPos=\"1\" iTs=\"2010-08-31T10:37:00\" >\n"
                + "\t\t<ct:DUnit dPos=\"1\" ID=\"Z51501\" leadVeh=\"Z515018\" Orientation=\"2\"/>\n"
                + "\t\t<ct:DUnit dPos=\"2\" ID=\"Z51510\" leadVeh=\"Z515101\" Orientation=\"1\"/>\n" + "\t</ct:DUnits>\n"
                + "\t<ct:Stop Id=\"3\" ChargeRT=\"32\" ArrivalT=\"2010-08-31T11:37:00\" DepartureT=\"2010-08-31T11:42:00\" DoorRelease_R=\"0\" DoorRelease_L=\"1\" Duration=\"300\">\n"
                + "\t\t<ct:Pis SiveId=\"ME2342\" StationId=\"NAN\" StationType=\"3\" ThTime=\"11:35:00\" DepStation=\"PAR\" ArrStation=\"NAN\" Status=\"1\" ModeT=\"1\"/>\n"
                + "\t\t<ct:GPS val=\"1\" lat=\"40.7231\" lon=\"1.66666\" dir=\"15.4\" Unit=\"5\"/>\n" + "\t\t<ct:Doors TotalIn=\"0\" TotalOut=\"2\" ExchangeT=\"210\">\n"
                + "\t\t\t<ct:Door Id=\"1\" In=\"0\" Out=\"0\" Unk=\"1\" VoitId=\"ZR1\" Err=\"1\" ErrSensor=\"1\"/>\n"
                + "\t\t\t<ct:Door Id=\"2\" In=\"0\" Out=\"1\" VoitId=\"ZR1\" />\n" + "\t\t\t<ct:Door Id=\"3\" In=\"0\" Out=\"1\" VoitId=\"ZR2\" />\n"
                + "\t\t\t<ct:Door Id=\"4\" In=\"0\" Out=\"0\" VoitId=\"ZR2\" Open=\"10\"/>\n" + "\t\t\t<ct:Door Id=\"5\" In=\"0\" Out=\"0\" VoitId=\"ZR3\" />\n"
                + "\t\t\t<ct:Door Id=\"6\" In=\"0\" Out=\"0\" VoitId=\"ZR3\" />\n" + "\t\t\t<ct:Door Id=\"7\" In=\"0\" Out=\"0\" VoitId=\"ZR4\" />\n"
                + "\t\t\t<ct:Door Id=\"8\" In=\"0\" Out=\"0\" Unk=\"1\" VoitId=\"ZR4\" />\n" + "\t\t\t<ct:DInt Id=\"1\" S1=\"3\" S2=\"1\" VoitId=\"ZR1\" />\n"
                + "\t\t\t<ct:DInt Id=\"2\" S1=\"0\" S2=\"4\" VoitId=\"ZR2\" />\n" + "\t\t\t<ct:DInt Id=\"3\" S1=\"3\" S2=\"5\" VoitId=\"ZR3\" />\n" + "\t\t</ct:Doors>\n"
                + "\t</ct:Stop>\n" + "</ct:APC>\n" + "\n";

        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xml)));

            NodeList nodeList = doc.getElementsByTagName("ct:GPS");
            int index=0;
            Map<Integer,String> listUrls=new HashMap<Integer, String>();
            listUrls.put(0,"URL 1");
            listUrls.put(1,"URL 2");
            listUrls.put(2,"URL 3");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("cpt:"+i);
                    // do something with the current element
                    if(node.getNodeName().equalsIgnoreCase("ct:GPS")){
                        Comment c2 = doc.createComment(listUrls.get(index)+index);
index+=1;


                        //Element comment = (Element) doc.createComment("googlemapsurl");
                        node.getParentNode().insertBefore(c2,node.getNextSibling());
                    }
                    System.out.println(node.getNodeName());
                }
            }


            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

            System.out.println(output);;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
