package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.time.Duration;

public class ContactHelper {
    private ApplicationManager manager;

    public ContactHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("home"));
        }
    }

    public boolean isContactPresent() {
        return !manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        click(By.xpath("(//input[@value=\'Delete\'])"));
    }

    public void createContact(ContactData contact) {
        openContactPage();
        type("firstname", contact.FirstName());
        type("middlename", contact.MiddleName());
        type("lastname", contact.LastName());
        //type("nickname", contact.Nickname());
        //type("title", contact.Title());
        //type("company", contact.Company());
        //type("address", contact.Address());
        type("home", contact.TelephoneHome());
        type("mobile", contact.TelephoneMobile());
        //type("work", contact.TelephoneWork());
        //type("fax", contact.TelephoneFax());
        //type("email", contact.Email());
        //type("email2", contact.Email2());
        //type("email3", contact.Email3());
        //type("homepage", contact.Homepage());
        manager.driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }

    private void type(String field, String contact) {
        manager.driver.findElement(By.name(field)).click();
        manager.driver.findElement(By.name(field)).sendKeys(contact);
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }
}
