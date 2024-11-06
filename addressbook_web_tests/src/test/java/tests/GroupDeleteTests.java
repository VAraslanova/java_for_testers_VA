package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        if (app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group 1", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }
}
