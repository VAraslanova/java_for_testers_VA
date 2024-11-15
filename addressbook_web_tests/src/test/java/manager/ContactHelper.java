package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper {
    private ApplicationManager manager;

    public ContactHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("home")).click();
        }
    }

    public boolean isContactPresent() {
        return !manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        openHomePage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("(//input[@value=\'Delete\'])")).click();
        /*driver.findElement(By.linkText("home")).click();*/
    }

    public void createContact(ContactData contact) {
        openContactPage();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.FirstName());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.MiddleName());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.LastName());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys(contact.Nickname());
        manager.driver.findElement(By.name("title")).click();
        manager.driver.findElement(By.name("title")).sendKeys(contact.Title());
        manager.driver.findElement(By.name("company")).click();
        manager.driver.findElement(By.name("company")).sendKeys(contact.Company());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.Address());
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys(contact.TelephoneHome());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.TelephoneMobile());
        manager.driver.findElement(By.name("work")).click();
        manager.driver.findElement(By.name("work")).sendKeys(contact.TelephoneWork());
        manager.driver.findElement(By.name("fax")).click();
        manager.driver.findElement(By.name("fax")).sendKeys(contact.TelephoneFax());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.Email());
        manager.driver.findElement(By.name("email2")).click();
        manager.driver.findElement(By.name("email2")).sendKeys(contact.Email2());
        manager.driver.findElement(By.name("email3")).click();
        manager.driver.findElement(By.name("email3")).sendKeys(contact.Email3());
        manager.driver.findElement(By.name("homepage")).click();
        manager.driver.findElement(By.name("homepage")).sendKeys(contact.Homepage());
        manager.driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }
}
