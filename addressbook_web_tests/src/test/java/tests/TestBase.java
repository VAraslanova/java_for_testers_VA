package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeAll
    public static void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome"));
    }
}
