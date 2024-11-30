package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
        public void canCreateContact() {
            app.contacts().createContact(new ContactData("", "First name", "Middle name", "Last name", //"Nickname", "Photo",
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
                        //result.add(new ContactData(firstname, "middlename", lastname, mobile, work));
                        result.add(new ContactData().withFirstName(firstname).withMiddleName("middlename").withLastName(lastname)
                                .withTelephoneMobile(mobile).withTelephoneHome(work));
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++){
            //result.add(new ContactData(randomString(i), "middlename", randomString(i), randomString(i), randomString(i)));
            result.add(new ContactData().withFirstName(randomString(i)).withMiddleName("middlename").withLastName(randomString(i))
                    .withTelephoneMobile(randomString(i)).withTelephoneHome(randomString(i)));
        }
        return result;
    }
    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withMiddleName("").withTelephoneHome("").withTelephoneMobile(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }
    }
