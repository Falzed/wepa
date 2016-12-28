//package wepa.selenium;
//
//import junit.framework.TestCase;
//import static org.junit.Assert.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.remote.*;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//    public class PicsTest extends TestCase {
//        private RemoteWebDriver driver;
//        public void setUp() throws Exception {
//                DesiredCapabilities capabillities = DesiredCapabilities.firefox();
//                capabillities.setCapability("version", "7");
//                capabillities.setCapability("platform", Platform.XP);
//                capabillities.setCapability("selenium-version", "2.18.0");
//                capabillities.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
//            driver = new RemoteWebDriver(
//                new URL("http://<username>:<api-key>@ondemand.saucelabs.com:80/wd/hub"),
//            capabillities);
//            driver.setFileDetector(new LocalFileDetector());
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//    }
//
////    public void UploadingPicWorks() throws Exception {
////        driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
////        WebElement upload = driver.findElement(By.id("myfile"));
////        upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");
////        driver.findElement(By.id("submit")).click();
////        driver.findElement(By.tagName("img"));
////        
////        assertTrue(pageSource().contains("1 kuvaa"));
////    }
//    public void tearDown() throws Exception {
//          driver.quit();
//
//    }
//}