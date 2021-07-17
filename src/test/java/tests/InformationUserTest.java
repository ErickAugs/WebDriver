package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.UnableToSetCookieException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformaçõesUsuarioTestData.csv")
public class InformationUserTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        //identificando o formulário de login
        WebElement formularioSingInBox = navegador.findElement(By.id("signinbox"));
        formularioSingInBox.click();

        //Digitar no campo name "login" que está dentro do formulário de Id "signinbox"
        // o texto com o nome do usuário Ex:"Use0001"
        formularioSingInBox.findElement(By.name("login")).sendKeys("braz001");

        //Digitar no campo name "password" que está dentro do formulário de Id "singinbox"
        // o texto com a senha do usuário Ex:"123456"
        formularioSingInBox.findElement(By.name("password")).sendKeys("0001");

        //Clicar no link com o texto "SING IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento class com nome "me",
        // está com o texto referente ao nome do usuário, ex; "Hi, User"
        WebElement me = navegador.findElement(By.className("me"));
        String textElementMe = me.getText(); //Retorna o texto contido entre o abrir e fechar da tag. No código <a id="mensagem">Este é meu texto</a>, o getText() retornaria "Este é meu texto".
        Assert.assertEquals("Hi, braz",textElementMe); //validação que esté teste passou corretamente.

        //clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //clicar em um link que possui um texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdcUmaInfoAdcionalDoUser(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada){
        //clicar no botão através no botão através do seu Xpath "//div[@id="moredata"]/div//button[@data-target="addmoredata"]"
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //clicar no popup onde está o formulário de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de nome "type" escolher a opção de "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //no Campo de name "contact" digitar "+5531999991111"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na msg de Id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement msgPopup = navegador.findElement(By.id("toast-container"));
        String msg = msgPopup.getText();
        Assert.assertEquals(mensagemEsperada,msg);
    }
    @Test
    public void  removerUmContatoUser(){
        //clicar no elemento pelo seu Xpath "//span[text()="+5531999991111"]/following-sibling::a"
        navegador.findElement(By.xpath("//span[text()=\"+5531999991111\"]/following-sibling::a")).click();

        //confirmar a janela js
        navegador.switchTo().alert().accept();

        //confirma que a msg apresentada é "Rest in peace, dear phone!"
        WebElement msgPopup = navegador.findElement(By.id("toast-container"));
        String msg = msgPopup.getText();
        Assert.assertEquals("Rest in peace, dear phone!",msg);
        String screenshotArquivo = "C:\\Users\\erickAugusto\\Desktop\\Projects\\test-report\\taskit\\"+ Generator.dateHoursArchive() + test.getMethodName() + ".png";
        Screenshot.shot(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça.
        WebDriverWait aguardar = new WebDriverWait(navegador,10);
        aguardar.until(ExpectedConditions.stalenessOf(msgPopup));

        //Clicar no link com o texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();


    }

    @After
    public void testDown(){
        //Fechar o navegador.
          navegador.quit();
    }
}
