package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactInGroup extends TestBase{

    @Test
    public void canAddContactToGroup() {
        //проверка наличия группы
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group 1", "group header", "group footer"));
        }

        var contact = new ContactData();
        //проверка наличия контакта
        if (app.hbm().getContactCount() == 0) {
            contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);
        }

        //проверка наличия контакта без группы
        List<GroupData> groups = app.hbm().getGroupList();
        int groupIndex = 0;
        var group = groups.get(groupIndex);

        List<ContactData> contacts = app.hbm().getContactList();
        //new ArrayList<ContactData>(app.contacts().getList());

        while (groupIndex < groups.size()) {
            var difference = new ArrayList<>(contacts);
            difference.removeAll(app.hbm().getContactsInGroup(groups.get(groupIndex)));
            if (!difference.isEmpty()) {
                contact = difference.get(0);
                group = groups.get(groupIndex);
                break;
            }
            groupIndex++;
        }

        if (groupIndex == groups.size()) {
            contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);
            contacts = app.hbm().getContactList();
            var maxId = contacts.get(contacts.size() - 1).id();
            contact = contact.withId(maxId);
            group = groups.get(0);
        }

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        // получить контакты в группе
        var oldRelated = app.hbm().getContactsInGroup(group);
        //добавить контакт в группу
        app.contacts().addContactToGroup(contact, group);
        //получить новые контакты в группе
        var newRelated = app.hbm().getContactsInGroup(group);
        //создать ожидаемый список с добавленным в нем контактом
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withPhoto(""));
        newRelated.sort(compareById);
        expectedList.sort(compareById);
        //сравнить
        Assertions.assertEquals(newRelated, expectedList);
    }


    @Test
    public void canDeleteContactFromGroup() {
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
        app.contacts().openHomePage();
        app.contacts().selectGroupMenu(group);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());
        app.contacts().removeContactFromGroup(oldRelated.get(0), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(0);
        app.contacts().openHomePage();
        var group0 = new GroupData().withName("[all]");
        app.contacts().selectGroupMenu(group0);
        Assertions.assertEquals(newRelated, expectedList);
    }
}
