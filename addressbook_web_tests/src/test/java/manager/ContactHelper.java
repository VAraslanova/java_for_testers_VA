package manager;

import io.qameta.allure.Step;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Step
    public void createContact(ContactData contact) {
        openContactPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void selectGroupMenu(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void selectGroupInHome(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void submitContactCreation() {
        manager.driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
    }

    private void type(String field, String text) {
        manager.driver.findElement(By.name(field)).clear();
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
        if (contact.Photo() != "") {
            attach("photo", contact.Photo());
        }
        type("address", contact.address());
        type("home", contact.home());
        type("mobile", contact.mobile());
        type("work", contact.work());
        type("email", contact.email());
        type("email2", contact.email2());
        type("email3", contact.email3());
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

    @Step
    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupInHome(group);
        click(By.name("add"));
    }

    @Step
    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupMenu(group);
        selectContact(contact);
        click(By.name("remove"));
    }

    @Step
    public String getAddress(ContactData contact) {
        manager.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    @Step
    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    @Step
    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    @Step
    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows= manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }
}
