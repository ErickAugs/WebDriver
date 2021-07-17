package suporte;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "erickaugusto_duRf5N";
    public static final String AUTOMATE_KEY = "yVYLtumxTz6jTn7gKjmy";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        //Abrindo o navegador (é utilizado o chromedriver para fazer o chamado com o browser)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\erickAugusto\\Drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Navegando para a página do Taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser","Chrome");
        caps.setCapability("browser_version","91.0");
        caps.setCapability("os","Windows");
        caps.setCapability("os_version","10");
        caps.setCapability("resolution","1280x800");
        caps.setCapability("browserstack.debug","true");

        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Navegando para a página do Taskit
            navegador.get("http://www.juliodelima.com.br/taskit");
        } catch (MalformedURLException e){
            System.out.println("Houveram problemas com a URL: " +e.getMessage());
        }

        return navegador;
    }
}
