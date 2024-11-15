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
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        return !manager.isElementPresent(By.name("selected[]"));
    }

    public void removeGroup() {
        openGroupPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        manager.driver.findElement(By.name("new")).click();
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }
}
