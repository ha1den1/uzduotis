package lt.viko.ad.rdelinda.tinklai.DB;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBTransformer {
    public static String marshal(Object object) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    public static <T> T unmarshal(String xml, Class<T> type) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return type.cast(unmarshaller.unmarshal(new StringReader(xml)));
    }
}
