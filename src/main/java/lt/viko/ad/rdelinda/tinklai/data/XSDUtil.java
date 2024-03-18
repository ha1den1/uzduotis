package lt.viko.ad.rdelinda.tinklai.data;

import java.io.FileWriter;
import java.io.IOException;

public class XSDUtil {
    public static void generateXSD() throws IOException {
        try (FileWriter writer = new FileWriter("student.xsd")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<xs:schema attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n");
            writer.write("    <xs:element name=\"student\">\n");
            writer.write("        <xs:complexType>\n");
            writer.write("            <xs:sequence>\n");
            writer.write("                <xs:element type=\"xs:byte\" name=\"id\"/>\n");
            writer.write("                <xs:element type=\"xs:string\" name=\"firstName\"/>\n");
            writer.write("                <xs:element type=\"xs:string\" name=\"lastName\"/>\n");
            writer.write("                <xs:element type=\"xs:string\" name=\"code\"/>\n");
            writer.write("                <xs:element name=\"account\">\n");
            writer.write("                    <xs:complexType>\n");
            writer.write("                        <xs:sequence>\n");
            writer.write("                            <xs:element type=\"xs:byte\" name=\"id\"/>\n");
            writer.write("                            <xs:element type=\"xs:string\" name=\"password\"/>\n");
            writer.write("                            <xs:element type=\"xs:string\" name=\"username\"/>\n");
            writer.write("                        </xs:sequence>\n");
            writer.write("                    </xs:complexType>\n");
            writer.write("                </xs:element>\n");
            writer.write("                <xs:element name=\"subjects\">\n");
            writer.write("                    <xs:complexType>\n");
            writer.write("                        <xs:sequence>\n");
            writer.write("                            <xs:element name=\"subject\" maxOccurs=\"unbounded\" minOccurs=\"0\">\n");
            writer.write("                                <xs:complexType>\n");
            writer.write("                                    <xs:sequence>\n");
            writer.write("                                        <xs:element type=\"xs:byte\" name=\"id\"/>\n");
            writer.write("                                        <xs:element type=\"xs:string\" name=\"name\"/>\n");
            writer.write("                                        <xs:element type=\"xs:byte\" name=\"credits\"/>\n");
            writer.write("                                    </xs:sequence>\n");
            writer.write("                                </xs:complexType>\n");
            writer.write("                            </xs:element>\n");
            writer.write("                        </xs:sequence>\n");
            writer.write("                    </xs:complexType>\n");
            writer.write("                </xs:element>\n");
            writer.write("            </xs:sequence>\n");
            writer.write("        </xs:complexType>\n");
            writer.write("    </xs:element>\n");
            writer.write("</xs:schema>\n");
            System.out.println("XSD schema generated and written to file: student.xsd");
        }
    }
}
