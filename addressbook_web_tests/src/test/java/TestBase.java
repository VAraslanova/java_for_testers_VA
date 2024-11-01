import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1905, 1022));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.name("pass")).sendKeys(Keys.ENTER);
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected void createContact(ContactData contact) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contact.FirstName());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(contact.MiddleName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contact.LastName());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(contact.Nickname());
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(contact.Title());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(contact.Company());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(contact.Address());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(contact.TelephoneHome());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(contact.TelephoneMobile());
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(contact.TelephoneWork());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(contact.TelephoneFax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(contact.Email());
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(contact.Email2());
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(contact.Email3());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(contact.Homepage());
        driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    // Остановилась тут!!!


    protected void openGroupPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected void openContactPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("add new")).click();
        }
    }

    protected boolean isGroupPresent() {
        return !isElementPresent(By.name("selected[]"));
    }

    protected void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }
}
