package lt.viko.ad.rdelinda.tinklai.DB;

import lt.viko.ad.rdelinda.tinklai.data.Student;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSocket {
    public void receiveXML() {
        try (Socket socket = new Socket("localhost", 9999)) {
            System.out.println("Connected to server: " + socket.getInetAddress());

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String xmlData = (String) inputStream.readObject();
            System.out.println("Received XML data from server:\n" + xmlData);

            try {
                Student student = TransformationManager.transformFromXML(xmlData);
                System.out.println("Unmarshalled Student object:\n" + student);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

