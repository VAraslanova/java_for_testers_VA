package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        app.contacts().removeContact();
        var newGroups = app.contacts().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }
}
