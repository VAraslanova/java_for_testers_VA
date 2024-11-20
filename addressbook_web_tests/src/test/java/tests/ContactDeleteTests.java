package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("First name", "Middle name", "Last name", //"Nickname", "Photo",
                    //"Title", "Company", "Address",
                    "Home", "Mobile"//, "Work", "Fax",
                    //"Email", "Email2", "Email3", "Homepage", "Birthday", "Anniversary", "Group"
                    ));
        }
        app.contacts().removeContact();
    }
}
