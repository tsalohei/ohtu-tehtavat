package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    //WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("command new user is selected")
    public void registerNewUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
    }
    
    //TYÖN ALLA
    //Given user with username "aa" and password "bad" is tried to be created
    @Given("user with username {string} and password {string} is tried to be created")
    public void accountIsTriedToBeCreatedWithInvalidUsernameAndInvalidPassword(String username, String password) {
        registerNewUserIsSelected();
        createUsernameAndPassword(username, password);
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }
    //When invalid username "aa" and invalid password "bad" are entered
    @When("invalid username {string} and invalid password {string} are entered") 
    public void tryingToLoginWithInvalidUsernameAndInvalidPassword(String username, String password) {
        logInWith(username, password);
    }

    //Then user is not logged in and error "invalid username or password" is reported
    @Then("user is not logged in and error {string} is reported")
    public void tryingToLogInWithInvalidCredentialsCausesErrorMessage(String message) {
        pageHasContent(message);
    }
    
    
    @Given("user with username {string} with password {string} is successfully created")
    public void accountIsCreated(String username, String password) {
        registerNewUserIsSelected();
        createUsernameAndPassword(username, password);
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
    }
    
    
    @When("valid username {string} and too short password {string} are entered")
    public void validUsernameAndTooShortPasswordAreEntered(String username, String password) {
        createUsernameAndPassword(username, password);
    }
    
    @When("valid username {string} and password {string} and password confirmation {string} are entered")
    public void passwordAndPasswordConfirmationDoNotMatch(String username, String password, String passwordConfirmation) {
        createUsernameAndPasswordWithBadPasswordConfirmation(username, password, passwordConfirmation);
    }
    
    @When("too short username {string} and valid password {string} are entered")
    public void tooShortUsernameAndValidPasswordAreEntered(String username, String password) {
        createUsernameAndPassword(username, password);
    }
    
    @Then("user is not created and error {string} is reported")
    public void tooShortUsernameCausesErrormessage(String message) {
        pageHasContent(message);
    }            
        
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernamePasswordAndPasswordConfirmationAreGiven(String username, String password) {
        createUsernameAndPassword(username, password);
    }
    
    @Then("a new user is created") 
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createUsernameAndPassword(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        
        element = driver.findElement(By.name("signup"));
        element.submit();
        
    }
    
    private void createUsernameAndPasswordWithBadPasswordConfirmation(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        
        element = driver.findElement(By.name("signup"));
        element.submit();
        
    }
}
