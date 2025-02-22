package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginHelper {

    private ApplicationManager manager;

    public LoginHelper(ApplicationManager manager){

        this.manager = manager;
    }
    void login(String user, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(user);
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.name("pass")).sendKeys(Keys.ENTER);
    }
}
