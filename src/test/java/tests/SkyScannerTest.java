package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.SkyScannerPage;
import service.UserCreator;

public class SkyScannerTest extends CommonConditions {

    @Test()
    public void test() {
        String destination = "Turin";
        User user = UserCreator.withCredentialsFromProperty();
        
        SkyScannerPage homePage = new SkyScannerPage(driver)
                .openPage()
                .login(user)
                .clickToHostelsTab()
                .addDestination(destination)
                .increaseRoom(10)
                .increaseAdult(10)
                .increaseChild(10);

        Assert.assertEquals(homePage.getQuantityRooms(), "5");
        Assert.assertEquals(homePage.getQuantityAdultPeople(), "10");
        Assert.assertEquals(homePage.getQuantityChildren(), "5");
    }
}
