package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase{
    @Test
    public void canModificateContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "firstname", "middlename", "lastname", "",
                    "address", "home", "mobile", "work", "email", "email2", "email3"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName(CommonFunctions.randomString(10));
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
//        Comparator<ContactData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newContacts.sort(compareById);
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newContacts, expectedList);

        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}
