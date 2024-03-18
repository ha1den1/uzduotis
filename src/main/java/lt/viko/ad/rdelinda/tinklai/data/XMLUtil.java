package lt.viko.ad.rdelinda.tinklai.data;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLUtil {
    public static <T> String marshalToXML(T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    public static <T> T unmarshalFromXML(String xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
    }
}
