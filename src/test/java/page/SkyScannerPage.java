package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.Util.waitForElementLocatedBy;


public class SkyScannerPage extends AbstractPage {
    private final String HOME_PAGE = "https://www.skyscanner.net/";
    private final Logger logger = LogManager.getRootLogger();

    // Captcha WebElement.
    private final By captchaElement = By.xpath("//*[contains(text(), 'Are you a person or a robot?')]");

    // Login WebElements.
    private final By loginButton = By.xpath("//button[@label='login-button']");
    private final By continueWithEmailButton = By.xpath("//span[contains(text(), 'Continue with email')]");
    private final By emailAddressInput = By.xpath("//input[@id='email']");
    private final By nextButton = By.xpath("//button[contains(text(), 'Next')]");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By submitButton = By.xpath("//button[contains(text(), 'Log in')]");
    private final By closeModalWindowButton = By.xpath("//button[@title='Close modal']");

    // Tab WebElements.
    private final By flightsTab = By.xpath("//nav[@id='PrimaryNav']//span[contains(text(), 'Flights')]");
    private final By hostelTab = By.xpath("//nav[@id='PrimaryNav']//span[contains(text(), 'Hotels')]");
    private final By carHireTab = By.xpath("//nav[@id='PrimaryNav']//span[contains(text(), 'Car Hire')]");

    // Hotels WebElements.
    private final By destinationOrHostelNameInput = By.xpath("//input[@name='destination-autosuggest']");
    private final By hostelCheckInInput = By.xpath("//input[@id='checkin']");
    private final By hostelCheckOutInput = By.xpath("//input[@id='checkout']");
    private final By guestsAndRoomsInput = By.xpath("//input[@id='guests-rooms']");
    private final By searchButton = By.xpath("//button[contains(text(), 'Search hotels')]");

    // Hotels guests and rooms WebElements.
    private final By quantityRoomsInput = By.xpath("//input[@id='rooms']");
    private final By increaseRoomButton = By.xpath("//button[@aria-controls='rooms'][@title='Increase']");
    private final By decreaseRoomButton = By.xpath("//button[@aria-controls='rooms'][@title='Decrease']");
    private final By quantityAdultPeopleInput = By.xpath("//input[@id='adults']");
    private final By increaseAdultButton = By.xpath("//button[@aria-controls='adults'][@title='Increase']");
    private final By decreaseAdultButton = By.xpath("//button[@aria-controls='adults'][@title='Decrease']");
    private final By quantityChildrenInput = By.xpath("//input[@id='children']");
    private final By increaseChildButton = By.xpath("//button[@aria-controls='children'][@title='Increase']");
    private final By decreaseChildButton = By.xpath("//button[@aria-controls='children'][@title='Decrease']");
    private final By doneButton = By.xpath("//footer/button");


    public SkyScannerPage(WebDriver driver) {
        super(driver);
    }

    // Check captcha on page.
    private void checkCaptchaOnPage() {
        Boolean answer = new WebDriverWait(driver, 4).until(
                ExpectedConditions.presenceOfElementLocated(captchaElement)).isDisplayed();
        System.out.println("ANSWER: " + answer);
        if (answer)
            throw new RuntimeException("The page consists captcha element.");
    }

    // Login.
    public SkyScannerPage login(User user) {
        waitForElementLocatedBy(driver, loginButton).click();
        waitForElementLocatedBy(driver, continueWithEmailButton).click();
        waitForElementLocatedBy(driver, emailAddressInput).sendKeys(user.getEmail());
        waitForElementLocatedBy(driver, nextButton).click();
        waitForElementLocatedBy(driver, passwordInput).sendKeys(user.getPassword());
        waitForElementLocatedBy(driver, submitButton).click();
        waitForElementLocatedBy(driver, closeModalWindowButton).click();
        return this;
    }

    // Click to tabs.
    public SkyScannerPage clickToFlightsTab() {
        waitForElementLocatedBy(driver, flightsTab).click();
        return this;
    }

    public SkyScannerPage clickToHostelsTab() {
        waitForElementLocatedBy(driver, hostelTab).click();
        return this;
    }

    public SkyScannerPage clickToCarHireTab() {
        waitForElementLocatedBy(driver, carHireTab).click();
        return this;
    }

    // Click to hotels elements.
    public SkyScannerPage addDestination(String destination) {
        waitForElementLocatedBy(driver, destinationOrHostelNameInput).click();
        waitForElementLocatedBy(driver, destinationOrHostelNameInput).sendKeys(destination);
        return this;
    }

    public SkyScannerPage increaseRoom() {
        waitForElementLocatedBy(driver, guestsAndRoomsInput).click();
        waitForElementLocatedBy(driver, increaseRoomButton).click();
        waitForElementLocatedBy(driver, doneButton).click();
        return this;
    }

    public SkyScannerPage increaseRoom(int quantity) {
        for (int i = 0; i < quantity; i++)
            increaseRoom();
        return this;
    }

    public SkyScannerPage increaseAdult() {
        waitForElementLocatedBy(driver, guestsAndRoomsInput).click();
        waitForElementLocatedBy(driver, increaseAdultButton).click();
        waitForElementLocatedBy(driver, doneButton).click();
        return this;
    }

    public SkyScannerPage increaseAdult(int quantity) {
        for (int i = 0; i < quantity; i++)
            increaseAdult();
        return this;
    }

    public SkyScannerPage increaseChild() {
        waitForElementLocatedBy(driver, guestsAndRoomsInput).click();
        waitForElementLocatedBy(driver, increaseChildButton).click();
        waitForElementLocatedBy(driver, doneButton).click();
        return this;
    }

    public SkyScannerPage increaseChild(int quantity) {
        for (int i = 0; i < quantity; i++)
            increaseChild();
        return this;
    }

    public String getQuantityRooms() {
        waitForElementLocatedBy(driver, guestsAndRoomsInput).click();
        String quantity = waitForElementLocatedBy(driver, quantityRoomsInput).getAttribute("value");
        waitForElementLocatedBy(driver, doneButton).click();
        return quantity;
    }

    public String getQuantityAdultPeople() {
        waitForElementLocatedBy(driver, guestsAndRoomsInput).click();
        String quantity = waitForElementLocatedBy(driver, quantityAdultPeopleInput).getAttribute("value");
        waitForElementLocatedBy(driver, doneButton).click();
        return quantity;
    }

    public String getQuantityChildren() {
        waitForElementLocatedBy(driver, guestsAndRoomsInput).click();
        String quantity = waitForElementLocatedBy(driver, quantityChildrenInput).getAttribute("value");
        waitForElementLocatedBy(driver, doneButton).click();
        return quantity;
    }

    @Override
    public SkyScannerPage openPage() {
        driver.get(HOME_PAGE);
        logger.info("Open: [" + HOME_PAGE + "]");
        checkCaptchaOnPage();
        return this;
    }
}
