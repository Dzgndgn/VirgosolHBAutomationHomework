package stepImplementation;

import com.thoughtworks.gauge.Step;
import pages.ChartClass;
import pages.ItemInfoPage;
import pages.ListingPage;
import pages.MainPage;

public class MainPageSteps {


    @Step("Kullanıcı ana sayfayada arama butonuna bilgisayar yazar ve tıklar")
    public void implementation1() throws InterruptedException {
        Thread.sleep(1000);
        MainPage mainPage = new MainPage();
        mainPage.search();
    }

    @Step("Kullanıcı listelenmiş ürünlerden istenen ürüne tıklayıp ürün bilgi sayfasına geçiş yapar")
    public void implementation2() {
        ListingPage listingPage = new ListingPage();
        listingPage.setListGridControl();
        listingPage.ClickSecondRowFirstItem();
    }

    @Step("Kullanıcı ürünü sepete ekler")
    public void implementation3() {
        ItemInfoPage itemInfoPage = new ItemInfoPage();
        itemInfoPage.ClickButtonToChart();
    }

    @Step("Kullanıcı sepetine giriş yapar")
    public void implementation4() {
        ChartClass chartClass = new ChartClass();
        chartClass.Checking();
    }
}
