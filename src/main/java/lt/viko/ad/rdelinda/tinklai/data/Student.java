    package lt.viko.ad.rdelinda.tinklai.data;

    import javax.persistence.*;
    import javax.xml.bind.annotation.*;
    import java.util.ArrayList;
    import java.util.List;

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    @Entity
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String firstName;
        private String lastName;
        private String code;

        @Transient
        @XmlElement(name = "account")
        private Account account;

        @XmlElementWrapper(name = "subjects")
        @XmlElement(name = "subject")
        @Transient
        private List<Subject> subjects;

        public Student() {
            this.subjects = new ArrayList<>();
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
        }
    }
