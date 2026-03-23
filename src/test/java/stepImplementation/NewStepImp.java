package stepImplementation;

import com.thoughtworks.gauge.Step;
import driver.LocatorManager;

public class NewStepImp {


    @Step("Listenin grid yapısında olduğu kontrol edilir")
    public void checkGrid() {
        LocatorManager.setListGridControl("productListContainer");
    }

    @Step("Listedeki 2.satırdaki ilk ürüne gidilir ve tıklanır")
    public void clickSecondItem() {
        LocatorManager.ClickSecondRowFirstItem("productItems");
    }



    @Step("<acceptCookieButton> butonuna tıklanır")
    public void implementation1(String acceptCookieButton) {
        LocatorManager.AcceptCookie(acceptCookieButton);
        //firstPage.clickBtn();
    }

    @Step("sonrasında <myAccountButton> butonuna tıkla")
    public void implementation2(String myAccountButton) {
        LocatorManager.ClickButton(myAccountButton);
    }

    @Step("<loginButton> butonuna tıkla ve gir")
    public void implementation3(String loginButton) {
        LocatorManager.ClickButton(loginButton);
    }

    @Step("<emailInput> alanına <email> değerini yazılır")
    public void implementation4(String emailInput, String email) {
        LocatorManager.FillTheBlanks(emailInput, LocatorManager.getData(email));
    }

    @Step("<passwordInput> alanına <password> değerini yazı")
    public void implementation5(String passwordInput, String password) {
        LocatorManager.FillTheBlanks(passwordInput, LocatorManager.getData(password));
    }

    @Step("<submitLoginButton> butonuna tıklanıp")
    public void implementation6(String submitLoginButton) {
        LocatorManager.ClickButton(submitLoginButton);
    }

    @Step("<searchArea> alanına tıklay")
    public void implementation7(String searchArea) {
        LocatorManager.ClickButton(searchArea);
        LocatorManager.ClickButton(searchArea);
    }


    @Step("<addToCartButton> butonuna tıkla ve ekle")
    public void implementation9(String addToCartButton) {
        LocatorManager.ScrollInto(addToCartButton);
        LocatorManager.ClickButton(addToCartButton);
    }

    @Step("<goToCartButton> butonuna tıkla ve git")
    public void implementation10(String goToCartButton) {
        LocatorManager.ScrollInto(goToCartButton);
        LocatorManager.ClickButton(goToCartButton);
    }


    @Step("<searchInput> alanına <item> yazılır ve <searchSuggestion> tıklanır ve geçiş yapar")
    public void implementation11(String searchInput, String item, String searchSuggestion) {
        LocatorManager.FillTheBlanks(searchInput, LocatorManager.getData(item));
        LocatorManager.ClickButton(searchSuggestion);
    }
}
