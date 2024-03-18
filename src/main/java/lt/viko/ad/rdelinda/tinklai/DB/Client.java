package lt.viko.ad.rdelinda.tinklai.DB;

import lt.viko.ad.rdelinda.tinklai.data.Account;
import lt.viko.ad.rdelinda.tinklai.data.Student;
import lt.viko.ad.rdelinda.tinklai.data.Subject;
import lt.viko.ad.rdelinda.tinklai.DB.HibernateUtil;
import lt.viko.ad.rdelinda.tinklai.DB.TransformationManager;

import lt.viko.ad.rdelinda.tinklai.data.XSDUtil;
import lt.viko.ad.rdelinda.tinklai.tests.TransformationManagerTest;
import org.hibernate.HibernateException;
import javax.xml.bind.JAXBException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lt.viko.ad.rdelinda.tinklai.data.DTDUtil.generateDTD;

public class Client {

    public static void main(String[] args) {
        try {
            org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            org.hibernate.Session session = sessionFactory.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();

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

            session.save(student);
            tx.commit();
            session.close();

            generateAndWriteXML(student);

            String xml = TransformationManager.transformToXML(student);
            System.out.println("XML Representation:");
            System.out.println(xml);
            try {
                generateAndWriteXML(student);
                generateDTD();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
            try {
                XSDUtil.generateXSD();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TransformationManagerTest.testTransformToXML();

            Student unmarshalledStudent = TransformationManager.transformToPOJO(xml, Student.class);
            if (unmarshalledStudent != null) {
                System.out.println("\nUnmarshalled Student:");
                System.out.println("Name: " + unmarshalledStudent.getFirstName() + " " + unmarshalledStudent.getLastName());
                System.out.println("Code: " + unmarshalledStudent.getCode());
                System.out.println("Account Username: " + unmarshalledStudent.getAccount().getUsername());
                System.out.println("Subjects:");
                for (Subject subject : unmarshalledStudent.getSubjects()) {
                    System.out.println("- Name: " + subject.getName() + ", Credits: " + subject.getCredits());
                }
            } else {
                System.out.println("Failed to unmarshal Student object.");
            }

        } catch (HibernateException | JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateAndWriteXML(Student student) throws JAXBException, IOException {
        String xml = TransformationManager.transformToXML(student);

        String xmlWithDTD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE student SYSTEM \"student.dtd\">\n" +
                xml.substring(xml.indexOf('\n') + 1);

        String correctedXml = xmlWithDTD.replace("<password>", "</username>\n    <password>")
                .replace("</account>", "</password>\n</account>");

        try (FileWriter writer = new FileWriter("student.xml")) {
            writer.write(correctedXml);
            System.out.println("XML document generated and written to file: student.xml");
        }
    }





}
