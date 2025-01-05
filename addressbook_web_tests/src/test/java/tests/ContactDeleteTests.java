package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDeleteTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "First name", "Middle name", "Last name", "",//"Nickname", "Photo",
                    //"Title", "Company",
                    "Address","Home", "Mobile"//, "Work", "Fax",
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


    @Test
    public void canDeleteContactInGroup() {
        //проверка наличия группы
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group 1", "group header", "group footer"));
        }

        //проверка, наличия группы с контактами
        List<GroupData> groups = app.hbm().getGroupList();
        int groupIndex = 0;
        var group = groups.get(groupIndex);
        while (app.hbm().getContactsInGroup(groups.get(groupIndex)).isEmpty()) {
            groupIndex++;
            if (groupIndex == groups.size()) {
                break;
            }
        }
        if (groupIndex == groups.size()) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            groupIndex = groupIndex - 1;
            app.contacts().createContact(contact, groups.get(groupIndex));
        }

        group = groups.get(groupIndex);
        app.contacts().selectGroupMenu(group);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());
        app.contacts().removeContactFromGroup(oldRelated.get(0), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(0);
        Assertions.assertEquals(newRelated, expectedList);
    }
}
