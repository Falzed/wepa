
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
public class TunnisteTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @LocalServerPort
    private Integer port;

    @Test
    public void addingTagWorks() {
        goTo("http://localhost:" + port);

        fill(find("#username")).with("käyttäjä");
        fill(find("#password")).with("salasana");
        submit(find("form").first());

        goTo("http://localhost:" + port + "/tunnisteet");
        fill(find("#nimi")).with("ensimmäinen");
        submit(find("form").first());
        
        assertTrue(pageSource().contains("ensimmäinen"));
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
