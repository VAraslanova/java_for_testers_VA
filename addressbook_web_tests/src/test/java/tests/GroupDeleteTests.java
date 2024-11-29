package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupDeleteTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group 1", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canDeleteAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group 1", "group header", "group footer"));
        }
        app.groups().removeAllGroup();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
