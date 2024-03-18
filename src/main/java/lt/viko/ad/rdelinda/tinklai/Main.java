package lt.viko.ad.rdelinda.tinklai;

import lt.viko.ad.rdelinda.tinklai.DB.ClientSocket;
import lt.viko.ad.rdelinda.tinklai.DB.HibernateUtil;
import lt.viko.ad.rdelinda.tinklai.DB.Server;
import lt.viko.ad.rdelinda.tinklai.DB.TransformationManager;
import lt.viko.ad.rdelinda.tinklai.data.Account;
import lt.viko.ad.rdelinda.tinklai.data.Student;
import lt.viko.ad.rdelinda.tinklai.data.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.xml.bind.JAXBException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lt.viko.ad.rdelinda.tinklai.data.DTDUtil.generateDTD;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setCode("JD001");

        Account account = new Account();
        account.setUsername("johndoe");
        account.setPassword("password");
        student.setAccount(account);

        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject();
        subject1.setName("Math");
        subject1.setCredits(5);
        Subject subject2 = new Subject();
        subject2.setName("Science");
        subject2.setCredits(4);
        subjects.add(subject1);
        subjects.add(subject2);
        student.setSubjects(subjects);

        saveStudent(student);

        try {
            generateAndWriteXML(student);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        try {
            generateAndWriteXML(student);
            generateDTD();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        new Server(student, 9999).start();

        new ClientSocket().receiveXML();
    }

    private static void saveStudent(Student student) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(student);

            tx.commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Failed to save student: " + e.getMessage());
        }
    }

    private static void generateAndWriteXML(Student student) throws JAXBException, IOException {
        String xml = TransformationManager.transformToXML(student);

        try (FileWriter writer = new FileWriter("student.xml")) {
            writer.write(xml);
            System.out.println("XML document generated and written to file: student.xml");
        } catch (IOException e) {
            System.err.println("Failed to write XML to file: " + e.getMessage());
            throw e;
        }
    }
}
