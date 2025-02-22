package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

    @Entity
    @Table(name = "addressbook")
    public class ContactRecord {

        public ContactRecord() {}

        public ContactRecord(int id, String firstname, String lastname, String address) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.address = address;
        }
        @Id
        public int id;
        public String firstname;
        public String lastname;
        public String address;
        public String home;
        public String mobile;
        public String work;
        public String email;
        public String email2;
        public String email3;
    }
