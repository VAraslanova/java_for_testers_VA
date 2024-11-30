package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{

    @Test
    public void canModificateGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group 1", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> comareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(comareById);
        expectedList.sort(comareById);
        Assertions.assertEquals(newGroups, expectedList);

    }
}