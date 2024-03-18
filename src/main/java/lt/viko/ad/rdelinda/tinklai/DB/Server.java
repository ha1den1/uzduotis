
package lt.viko.ad.rdelinda.tinklai.DB;

import lt.viko.ad.rdelinda.tinklai.data.Student;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private Student student;
    private int port;

    public Server(Student student, int port) {
        this.student = student;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Listening on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                String xmlData = TransformationManager.transformToXML(student);

                outputStream.writeObject(xmlData);
                System.out.println("XML data sent to client.");

                outputStream.close();
                clientSocket.close();
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
