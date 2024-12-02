package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ContactDeleteTests extends TestBase {

    @Test //в приложении ошибка: контакты не удаляются, проверяем, что список не меняется
    public void canNotDeleteContact() {
        if (app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "First name", "Middle name", "Last name", //"Nickname", "Photo",
                    //"Title", "Company", "Address",
                    "Home", "Mobile"//, "Work", "Fax",
                    //"Email", "Email2", "Email3", "Homepage", "Birthday", "Anniversary", "Group"
                    ));
        }
        var oldGroups = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.contacts().removeContact(oldGroups.get(index));
        var newGroups = app.contacts().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }
}
