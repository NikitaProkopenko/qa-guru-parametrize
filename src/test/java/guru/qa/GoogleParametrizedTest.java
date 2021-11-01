package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import guru.qa.domain.MenuItem;
import guru.qa.page.GoogleResultPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class GoogleParametrizedTest {
    private GoogleResultPage google = new GoogleResultPage();

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

    @EnumSource(MenuItem.class)
    @ParameterizedTest(name = "Check Google search results on different tabs")
    void checkGoogleSearchResultOnDifferentTabs(MenuItem menuItem) {
        open("https://www.google.com/");
        $(By.name("q")).setValue("github").pressEnter();
        google.switchToMenuItem(menuItem);
        $(By.name("q")).shouldHave(Condition.attribute("value", "github"));
    }

    static Stream<Arguments> checkGoogleSearchResultOnDifferentTabsMethodSource() {
        return Stream.of(
                Arguments.of(
                    "All"
                ),
                Arguments.of(
                    "Images"
                ),
                Arguments.of(
                    "Videos"
                )
        );
    }
    @MethodSource()
    @ParameterizedTest(name = "Check Google search results on different tabs")
    void checkGoogleSearchResultOnDifferentTabsMethodSource(String tabName) {
        open("https://www.google.com/");
        $(By.name("q")).setValue("github").pressEnter();
        $$("#hdtb-msb .hdtb-mitem").find(Condition.text(tabName)).click();
        $(By.name("q")).shouldHave(Condition.attribute("value", "github"));
    }
}
