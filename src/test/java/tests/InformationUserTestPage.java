package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

public class InformationUserTestPage {
    private WebDriver navegador;

    @Before
    public void SetUp(){
        navegador = Web.createBrowserStack();
    }
    @Test
    public void testAdicionarInfoAdicionalUser() {
        String textoToast = new LoginPage(navegador)
                .clicarLogin()
                .fazerLogin("braz001", "0001")
                .clicarMe()
                .clicarNaAbaMoreDataAbout()
                .clicarBotaoAddMoreDateAboutYou()
                .adcionarContato("Phone","+5531931314444")
                .capturarTextoToast();
        Assert.assertEquals("Your contact has been added!", textoToast);
    }
    @After
    public void TearDown(){
        navegador.quit();
    }
}
