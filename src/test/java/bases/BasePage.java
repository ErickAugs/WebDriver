package bases;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
    //Variaveis globlais
    protected WebDriver navegador;
    protected WebDriverWait wait = null;
    protected long timeOutDefault;

    public BasePage(WebDriver navegador){
        this.navegador = navegador;
        navegador.manage().window().maximize();
    }
    public String capturarTextoToast(){
        //Na msg de Id "toast-container" validar que o texto é "Your contact has been added!"
        return navegador.findElement(By.id("toast-container")).getText();
    }
}
