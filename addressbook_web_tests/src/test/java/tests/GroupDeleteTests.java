import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        app.openGroupPage();
        if (app.isGroupPresent()) {
            app.createGroup(new GroupData("group 1", "group header", "group footer"));
        }
        app.removeGroup();
    }
}
