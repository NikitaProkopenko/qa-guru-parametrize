package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleParametrizedTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @ValueSource(strings = {
            "github",
            "gitlab"
    })
    @ParameterizedTest(name = "Check Google input search value with query: {0}")
    void checkGoogleSearchResult(String query) {
        open("https://www.google.com/");
        $(By.name("q")).setValue(query).pressEnter();
        $(By.name("q")).shouldHave(Condition.attribute("value", query));
    }

    @CsvSource({
            "github, GitHub",
            "gitlab, GitLab"
    })
    @ParameterizedTest(name = "Check Google input search value with query: {0} and additional info box title: {1}")
    void checkGoogleSearchComplexResult(String query, String infoBoxTitle) {
        open("https://www.google.com/");
        $(By.name("q")).setValue(query).pressEnter();
        $(By.name("q")).shouldHave(Condition.attribute("value", query));
        $(By.cssSelector("h2[data-attrid=title]")).shouldHave(Condition.text(infoBoxTitle));
    }
}
