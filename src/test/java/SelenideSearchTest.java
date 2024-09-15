import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideSearchTest {
    @BeforeAll
    static void beforeall() {
        Configuration.browserSize = "1920x1080";
        Configuration.reopenBrowserOnFail = false;
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void SelenideWikiTest() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $(".markdown-body ul").shouldHave(text("Soft assertions"));
        $(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                        
                """));
    }
}