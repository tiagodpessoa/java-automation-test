package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class PracticeFormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();

         ((JavascriptExecutor) driver).executeScript(
        "document.querySelectorAll('iframe').forEach(el => el.remove());" +
        "document.querySelectorAll('[id*=\"google_ads\"]').forEach(el => el.remove());"
    );
    }

    public void setFirstName(String name) {
        driver.findElement(By.id("firstName")).sendKeys(name);
    }

    public void setLastName(String lastName) {
        driver.findElement(By.id("lastName")).sendKeys(lastName);
    }

    public void setEmail(String email) {
        driver.findElement(By.id("userEmail")).sendKeys(email);
    }

    public void selectGender(String gender) {
        driver.findElement(By.xpath("//label[text()='" + gender + "']")).click();
    }

    public void setPhoneNumber(String number) {
        driver.findElement(By.id("userNumber")).sendKeys(number);
    }

    public void setDateOfBirth(String day, String month, String year) {
        driver.findElement(By.id("dateOfBirthInput")).click();
        new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByVisibleText(month);
        new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByVisibleText(year);
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")).click();
    }

    public void setSubject(String subjectName) {
        WebElement subject = driver.findElement(By.id("subjectsInput"));
        subject.sendKeys(subjectName);
        subject.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        driver.findElement(By.xpath("//label[text()='" + hobby + "']")).click();
    }

    public void uploadPicture(String path) {
        driver.findElement(By.id("uploadPicture")).sendKeys(path);
    }

    public void setCurrentAddress(String address) {
        driver.findElement(By.id("currentAddress")).sendKeys(address);
    }

    public void selectState(String stateName) {
        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys(stateName);
        state.sendKeys(Keys.ENTER);
    }

    public void selectCity(String cityName) {
        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys(cityName);
        city.sendKeys(Keys.ENTER);
    }

    public void clickSubmit() {
        driver.findElement(By.id("submit")).click();
    }

    public boolean isSubmissionModalDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
        return driver.findElement(By.id("example-modal-sizes-title-lg")).isDisplayed();
    }

    public String getModalTableText() {
        return driver.findElement(By.className("table-responsive")).getText();
    }
}
