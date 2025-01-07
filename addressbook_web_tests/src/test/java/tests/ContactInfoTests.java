package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testAllPhones() {
        var contacts = app.hbm().getContactList();
        // проверить, что контакты есть, если нет, то создать
        if (contacts.size() == 0) {
            var newContact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                    .withHome(CommonFunctions.randomNumber(10))
                    .withMobile(CommonFunctions.randomNumber(10))
                    .withWork(CommonFunctions.randomNumber(10));
            app.contacts().createContact(newContact);
        }

        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
    ));

        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testPhones() {
        if (app.hbm().getContactList().size() == 0) {
            var newContact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withHome(CommonFunctions.randomNumber(10))
                    .withMobile(CommonFunctions.randomNumber(10))
                    .withWork(CommonFunctions.randomNumber(10));
            app.contacts().createContact(newContact);
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);

        var expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expectedPhones, phones);
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactList().size() == 0) {
            var newContact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10));
            app.contacts().createContact(newContact);
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var address = app.contacts().getAddress(contact);

        var expectedAddress = Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expectedAddress, address);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactList().size() == 0) {
            var newContact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomEmail(10))
                    .withEmail2(CommonFunctions.randomEmail(10))
                    .withEmail3(CommonFunctions.randomEmail(10));
            app.contacts().createContact(newContact);
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);

        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expectedEmails, emails);
    }
}
