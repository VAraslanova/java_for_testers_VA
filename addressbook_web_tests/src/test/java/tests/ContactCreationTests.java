package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
        public void canCreateContact() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);
        }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group 1", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

        @Test
        public void canCreateContactWithEmptyName() {
            app.contacts().createContact(new ContactData());
        }

        @Test
        public void canContactWithNameOnly() {
            var emptyContact = new ContactData();
            var contactWithName = emptyContact.withFirstName("someName");
            app.contacts().createContact(contactWithName);
        }

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();

//        for (var firstname:List.of("","contactFirstname")){
//            for (var lastname:List.of("", "contactLastname")){
//                for (var mobile:List.of("", "contactMobile")){
//                    for (var work:List.of("", "contactWork")) {
//                        //result.add(new ContactData(firstname, "middlename", lastname, mobile, work));
//                        result.add(new ContactData().withFirstName(firstname).withMiddleName("middlename").withLastName(lastname)
//                                .withTelephoneMobile(mobile).withTelephoneHome(work));
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < 3; i++){
//            //result.add(new ContactData(randomString(i), "middlename", randomString(i), randomString(i), randomString(i)));
//            result.add(new ContactData().withFirstName(CommonFunctions.randomString(i)).withMiddleName("middlename").withLastName(CommonFunctions.randomString(i))
//                    .withTelephoneMobile(CommonFunctions.randomString(i)).withTelephoneHome(CommonFunctions.randomString(i)));
//        }

        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }
    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId).withPhoto(""));//.withMiddleName("").withTelephoneHome("").withTelephoneMobile("").withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }
    }
