
package wepa.selenium;

import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @LocalServerPort
    private Integer port;

    @Test
    public void registeringRedirectsToSignIn() {
        goTo("http://localhost:" + port);

        click(find("#linkkirekisterointiin"));
        assertTrue(pageSource().contains("Register"));

        fill(find("#username")).with("kayttaja");
        fill(find("#password")).with("salasana");
        submit(find("#registerlomake"));

        assertTrue(pageSource().contains("Sign in"));
    }
    
    @Test
    public void loginWorks() {
        goTo("http://localhost:" + port);

        fill(find("#username")).with("admin");
        fill(find("#password")).with("admin");
        submit(find("form").first());

        assertTrue(pageSource().contains("Kuvapalvelu"));
    }
}
