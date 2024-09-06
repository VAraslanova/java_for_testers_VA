import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        openGroupPage();
        if (isGroupPresent()) {
            createGroup("group 1", "group header", "group footer");
        }
        removeGroup();
    }
}
