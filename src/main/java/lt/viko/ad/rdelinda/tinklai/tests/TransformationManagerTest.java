package lt.viko.ad.rdelinda.tinklai.tests;

import lt.viko.ad.rdelinda.tinklai.data.Account;
import lt.viko.ad.rdelinda.tinklai.data.Student;
import lt.viko.ad.rdelinda.tinklai.data.Subject;
import lt.viko.ad.rdelinda.tinklai.data.XMLUtil;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class TransformationManagerTest {

    @Test
    public static void testTransformToXML() throws JAXBException {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setCode("JD001");

        Account account = new Account();
        account.setId(1);
        account.setUsername("johndoe");
        account.setPassword("password");
        student.setAccount(account);

        Subject subject1 = new Subject();
        subject1.setId(1);
        subject1.setName("Math");
        subject1.setCredits(5);

        Subject subject2 = new Subject();
        subject2.setId(2);
        subject2.setName("Science");
        subject2.setCredits(4);

        student.getSubjects().add(subject1);
        student.getSubjects().add(subject2);

        String xml = XMLUtil.marshalToXML(student);

        assertNotNull(xml);
    }

    @Test
    public void testTransformFromXML() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<student>" +
                "    <id>1</id>" +
                "    <firstName>John</firstName>" +
                "    <lastName>Doe</lastName>" +
                "    <code>JD001</code>" +
                "    <account>" +
                "        <id>1</id>" +
                "        <password>password</password>" +
                "        <username>johndoe</username>" +
                "    </account>" +
                "    <subjects>" +
                "        <subject>" +
                "            <id>1</id>" +
                "            <name>Math</name>" +
                "            <credits>5</credits>" +
                "        </subject>" +
                "        <subject>" +
                "            <id>2</id>" +
                "            <name>Science</name>" +
                "            <credits>4</credits>" +
                "        </subject>" +
                "    </subjects>" +
                "</student>";

        Student student = XMLUtil.unmarshalFromXML(xml, Student.class);

        assertNotNull(student);
        assertEquals(1, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("JD001", student.getCode());
        assertNotNull(student.getAccount());
        assertEquals(1, student.getAccount().getId());
        assertEquals("johndoe", student.getAccount().getUsername());
        assertEquals("password", student.getAccount().getPassword());
        assertEquals(2, student.getSubjects().size());
    }
}
