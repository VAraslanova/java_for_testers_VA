import model.ContactData;
import org.junit.jupiter.api.Test;

    public class ContactCreationTests extends TestBase {
        @Test
        public void canCreateContact() {
            openContactPage();
            createContact(new ContactData("group 1", "group header", "group footer"));
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
