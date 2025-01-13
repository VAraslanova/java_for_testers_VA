package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase{

    public static Stream<String> randomUsers() throws IOException {
        Supplier<String> randomUsers = () -> CommonFunctions.randomString(5);
        return Stream.generate(randomUsers).limit(1);

    }

    @ParameterizedTest
    @MethodSource ("randomUsers")
    void canRegisterUser(String username) {
        var password = "password";
        var email = String.format("%s@localhost", username);
        //создать пользователя на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(email, password);
        //app.jamesApi().addUser(email, password);

        //в браузере заполнить форму создания и отправить (браузер)
        app.http().login("administrator", "root");
        app.browser().registrationUser(username);

        //получаем почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));

        //извлекаем ссылку из письма
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        System.out.println(url);

        //проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.browser().confirmationRegistration(url, username, password);

        //проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }


    public static Stream<UserData> randomUserData() throws IOException {
        Supplier<UserData> randomUsers = () -> new UserData()
                .withUsername(CommonFunctions.randomString(5))
                .withRealname(CommonFunctions.randomString(5))
                .withPassword("password");
        return Stream.generate(randomUsers).limit(1);

    }


    @ParameterizedTest
    @MethodSource ("randomUserData")
    void canRegisterUserWithRestApi(UserData user) {
        //var password = "password";
        user = user.withEmail(String.format("%s@localhost", user.username()));
        //создать пользователя на почтовом сервере (JamesHelper)
        //app.jamesCli().addUser(email, password);
        app.jamesApi().addUser(user.email(), user.password());

        //в браузере заполнить форму создания и отправить (браузер)
        app.rest().createUser(user);

        //получаем почту (MailHelper)
        var messages = app.mail().receive(user.email(), user.password(), Duration.ofSeconds(10));

        //извлекаем ссылку из письма
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        System.out.println(url);

        //проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.browser().confirmationRegistration(url, user.username(), user.password());

        //проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(user.username(), user.password());
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
