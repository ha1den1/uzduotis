package lt.viko.ad.rdelinda.tinklai.data;

import java.io.FileWriter;
import java.io.IOException;

public class DTDUtil {
    public static void generateDTD() throws IOException {
        try (FileWriter writer = new FileWriter("student.dtd")) {
            writer.write("<!ELEMENT student (id, firstName, lastName, code, account?, subjects?)>\n");
            writer.write("<!ELEMENT id (#PCDATA)>\n");
            writer.write("<!ELEMENT firstName (#PCDATA)>\n");
            writer.write("<!ELEMENT lastName (#PCDATA)>\n");
            writer.write("<!ELEMENT code (#PCDATA)>\n");
            writer.write("<!ELEMENT account (id, username, password)>\n");
            writer.write("<!ELEMENT subjects (subject*)>\n");
            writer.write("<!ELEMENT subject (id, name, credits)>\n");
            writer.write("<!ELEMENT username (#PCDATA)>\n");
            writer.write("<!ELEMENT password (#PCDATA)>\n");
            writer.write("<!ELEMENT name (#PCDATA)>\n");
            writer.write("<!ELEMENT credits (#PCDATA)>\n");
            writer.write("<!ATTLIST id xmlns CDATA #IMPLIED>\n");
            writer.write("<!ATTLIST account xmlns CDATA #IMPLIED>\n");
            writer.write("<!ATTLIST subjects xmlns CDATA #IMPLIED>\n");
            writer.write("<!ATTLIST subject xmlns CDATA #IMPLIED>\n");
            System.out.println("DTD schema generated and written to file: student.dtd");
        }
    }
}
