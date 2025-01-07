package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactDeleteTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "First name", "Middle name", "Last name", "",//"Nickname", "Photo",
                    //"Title", "Company",
                    "Address","Home", "Mobile", "Work"//, "Fax",
                    //"Email", "Email2", "Email3", "Homepage", "Birthday", "Anniversary", "Group"
                    ));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newGroups = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
