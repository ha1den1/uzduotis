package lt.viko.ad.rdelinda.tinklai.DB;

import javax.xml.bind.*;
import lt.viko.ad.rdelinda.tinklai.data.Student;

import java.io.StringReader;
import java.io.StringWriter;

public class TransformationManager {
    public static String transformToXML(Student student) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();

        marshaller.marshal(student, writer);

        return writer.toString();
    }

    public static Student transformFromXML(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Student) unmarshaller.unmarshal(new StringReader(xml));
    }

    public static <T> T transformToPOJO(String xml, Class<T> type) throws JAXBException {
        return JAXBTransformer.unmarshal(xml, type);
    }
}
