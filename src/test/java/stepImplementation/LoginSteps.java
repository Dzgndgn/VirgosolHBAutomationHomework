package stepImplementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import pages.FirstPage;
import pages.LoginPage;

public class LoginSteps extends Driver {


    @Step("Kullanıcı Hepsburada internet sitesine gider ve giriş yap butonuna tıklar")
    public void implementation1() {

        Driver.webDriver.get("https://www.hepsiburada.com/");
        System.out.println("stepdeneme");
        //firstPage.GoToSite();
        FirstPage firstPage = new FirstPage();
        firstPage.clickBtn();
    }

    @Step("Kullanıcı email ve password girerek başarılı şekilde giriş yapar")
    public void implementation2() throws InterruptedException {
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage();
        loginPage.FillTheBlanks();

    }






}
