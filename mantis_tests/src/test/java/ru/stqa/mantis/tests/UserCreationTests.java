package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{

    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developer.com", user.name());

        app.user().startCreation(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(10));

        var text = message;
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        System.out.println(url);

        app.browser().confirmationRegistration(url, user.name(), password);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
