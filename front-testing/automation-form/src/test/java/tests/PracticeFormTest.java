package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PracticeFormPage;

import static org.junit.jupiter.api.Assertions.*;

public class PracticeFormTest {
    private WebDriver driver;
    private PracticeFormPage formPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().cachePath("~/.cache/selenium").setup();
        driver = new ChromeDriver();
        formPage = new PracticeFormPage(driver);
        formPage.open();
    }

    @Test
    @DisplayName("Deve preencher e enviar o formulário com sucesso")
    void testSuccessfulFormSubmission() {
        formPage.setFirstName("João");
        formPage.setLastName("da Silva");
        formPage.setEmail("joao@email.com");
        formPage.selectGender("Male");
        formPage.setPhoneNumber("9999999999");
        formPage.setDateOfBirth("10", "October", "1990");
        formPage.setSubject("Maths");
        formPage.selectHobby("Sports");
        formPage.uploadPicture(System.getProperty("user.dir") + "/src/test/resources/testfile.jpg");
        formPage.setCurrentAddress("Rua dos Testes, 123");
        formPage.selectState("NCR");
        formPage.selectCity("Delhi");
        formPage.clickSubmit();

        assertTrue(formPage.isSubmissionModalDisplayed(), "O modal de confirmação não foi exibido");

        String modalText = formPage.getModalTableText();
        assertTrue(modalText.contains("João da Silva"));
        assertTrue(modalText.contains("joao@email.com"));
        assertTrue(modalText.contains("Male"));
        assertTrue(modalText.contains("9999999999"));
        assertTrue(modalText.contains("Maths"));
        assertTrue(modalText.contains("Sports"));
        assertTrue(modalText.contains("Rua dos Testes, 123"));
        assertTrue(modalText.contains("NCR Delhi"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
