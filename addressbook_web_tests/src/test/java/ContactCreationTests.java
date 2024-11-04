import model.ContactData;
import org.junit.jupiter.api.Test;

    public class ContactCreationTests extends TestBase {
        @Test
        public void canCreateContact() {
            openContactPage();
            createContact(new ContactData("First name", "Middle name", "Last name", "Nickname", "Photo",
                    "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax",
                    "Email", "Email2", "Email3", "Homepage", "Birthday", "Anniversary", "Group"));
        }

        @Test
        public void canCreateContactWithEmptyName() {
            openContactPage();
            createContact(new ContactData());
        }

        @Test
        public void canContactWithNameOnly() {
            openContactPage();
            var emptyContact = new ContactData();
            var contactWithName = emptyContact.withFirstName("some name");
            createContact(contactWithName);
        }
    }


}
