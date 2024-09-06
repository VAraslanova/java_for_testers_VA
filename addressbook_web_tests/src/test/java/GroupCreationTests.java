import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {
    @Test
    public void canCreateGroup() {
        openGroupPage();
        createGroup("group 1", "group header", "group footer");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupPage();
        createGroup("", "", "");
    }
}
