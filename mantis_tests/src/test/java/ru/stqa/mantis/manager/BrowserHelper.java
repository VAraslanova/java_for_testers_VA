package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class BrowserHelper extends HelperBase{
    public BrowserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openURL(String url) {
        manager.driver().get(url);
    }

    public void registrationUser(String username) {
        var loginUrl = String.format("%s/login_page.php",manager.property("web.baseUrl"));
        openURL(loginUrl);
        click(By.xpath("//a[text()='Signup for a new account']"));
        type(By.name("username"), username);
        type(By.name("email"), String.format("%s@localhost", username));
        click(By.xpath("//input[@value='Signup']"));
        //<a class="width-40 btn btn-inverse bigger-110 btn-success" href="login_page.php">
        //									Proceed								</a>
    }

}
