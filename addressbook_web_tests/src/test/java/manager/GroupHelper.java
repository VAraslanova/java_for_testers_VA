package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.time.Duration;

public class GroupHelper {
    private ApplicationManager manager;

    public GroupHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        removeSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void returnToGroupsPage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        if (manager.isElementPresent(By.linkText("group page"))) {
            click(By.linkText("group page"));
        }
        else {
            click(By.linkText("groups"));
        }
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroup() {
        openGroupPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes){
            checkbox.click();
        }
    }
}
