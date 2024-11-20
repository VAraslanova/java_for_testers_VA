package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
        public void canCreateContact() {
            app.contacts().createContact(new ContactData("First name", "Middle name", "Last name", //"Nickname", "Photo",
                    //"Title", "Company", "Address",
                    "Home", "Mobile"//, "Work", "Fax",
                    //"Email", "Email2", "Email3", "Homepage", "Birthday", "Anniversary", "Group"
            ));
        }

        @Test
        public void canCreateContactWithEmptyName() {
            app.contacts().createContact(new ContactData());
        }

        @Test
        public void canContactWithNameOnly() {
            var emptyContact = new ContactData();
            var contactWithName = emptyContact.withFirstName("some name");
            app.contacts().createContact(contactWithName);
        }

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();

        for (var firstname:List.of("","contactFirstname")){
            for (var lastname:List.of("", "contactLastname")){
                for (var mobile:List.of("", "contactMobile")){
                    for (var work:List.of("", "contactWork")) {
                        result.add(new ContactData(firstname, "middlename", lastname, mobile, work));
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++){
            result.add(new ContactData(randomString(i), "middlename", randomString(i), randomString(i), randomString(i)));
        }
        return result;
    }
    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleGroups(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
    }
