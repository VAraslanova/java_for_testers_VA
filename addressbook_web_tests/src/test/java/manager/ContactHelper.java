package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;

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

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        click(By.xpath("(//input[@value=\'Delete\'])"));
    }

    public void createContact(ContactData contact) {
        openContactPage();
        type("firstname", contact.firstName());
        type("middlename", contact.middleName());
        type("lastname", contact.lastName());
        if (contact.Photo() != "") {
            attach("photo", contact.Photo());
        }
        //type("nickname", contact.Nickname());
        //type("title", contact.Title());
        //type("company", contact.Company());
        //type("address", contact.Address());
        type("home", contact.telephoneHome());
        type("mobile", contact.telephoneMobile());
        //type("work", contact.TelephoneWork());
        //type("fax", contact.TelephoneFax());
        //type("email", contact.Email());
        //type("email2", contact.Email2());
        //type("email3", contact.Email3());
        //type("homepage", contact.Homepage());
        manager.driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        //manager.driver.findElement(By.linkText("home page")).click();
        returnToContactPage();
    }

    private void type(String field, String text) {
        manager.driver.findElement(By.name(field)).click();
        manager.driver.findElement(By.name(field)).sendKeys(text);
    }

    private void attach(String field, String file) {
        manager.driver.findElement(By.name(field)).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
        //click(By.name("selected[]"));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']/img", contact.id())));
    }

    private void fillContactForm(ContactData contact) {
        type("firstname", contact.firstName());
        type("middlename", contact.middleName());
        type("lastname", contact.lastName());
        type("home", contact.telephoneHome());
        type("mobile", contact.telephoneMobile());
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void returnToContactPage() {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        if (manager.isElementPresent(By.linkText("home page"))) {
            click(By.linkText("home page"));
        }
        else {
            click(By.linkText("home"));
        }
    }


    public ArrayList<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var lines = manager.driver.findElements(By.name("entry"));
        for (var line : lines) {
            var checkbox = line.findElement(By.name("selected[]"));
            var firstName = line.findElements(By.tagName("td")).get(2).getText();
            var lastName = line.findElements(By.tagName("td")).get(1).getText();
            var id = checkbox.getAttribute("id");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactPage();
    }




}
