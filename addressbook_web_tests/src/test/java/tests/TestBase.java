package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeAll
    public static void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome"));
    }

    public static String randomString(int n){
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++){
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }

}
