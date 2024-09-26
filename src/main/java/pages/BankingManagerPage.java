package pages;

import exceptions.NotRightPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.PropertiesLoader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BankingManagerPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'Add Customer')]")
    WebElement addCustomer;

    @FindBy(xpath = "//button[contains(text(),'Customers')]")
    WebElement customers;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    WebElement postCode;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//a[contains(text(),'First Name')]")
    WebElement firstNameColumnInTable;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    WebElement firstNameInSortedTable;

    @FindBy(xpath = "//tbody/tr[1]/td[5]/button")
    WebElement deleteCustomer;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> customersTable;

    public final String pathToPage;

    public BankingManagerPage(WebDriver driver) {
        super(driver);
        pathToPage = PropertiesLoader.load().getProperty("path.to.manager");
        PageFactory.initElements(driver, this);
    }

    public BankingManagerPage verifyPage() throws NotRightPageException {
        String currentUrl = driver.getCurrentUrl();
        String targetUrl = baseUrl + pathToPage;
        if (!(targetUrl).equals(currentUrl)) {
            throw new NotRightPageException(currentUrl);
        }
        return this;
    }

    @Override
    public BankingManagerPage open() {
        driver.get(super.baseUrl + pathToPage);
        return this;
    }

    public BankingManagerPage clickAddCustomerButton() {
        waitElementToBeVisible(addCustomer);
        addCustomer.click();
        return this;
    }

    public BankingManagerPage clickCustomersButton() {
        waitElementToBeVisible(customers);
        customers.click();
        return this;
    }

    public BankingManagerPage setFirstname(String costomerFirstname) {
        waitElementToBeClickable(firstName);
        firstName.sendKeys(costomerFirstname);
        return this;
    }

    public BankingManagerPage setLastname(String costomerLastname) {
        lastName.sendKeys(costomerLastname);
        return this;
    }

    public BankingManagerPage setPostCode(String costomerPostCode) {
        postCode.sendKeys(costomerPostCode);
        return this;
    }

    public BankingManagerPage clickToSbmt() {
        submitButton.click();
        return this;
    }

    public String getaAlertText() {
        return driver.switchTo().alert().getText();
    }

    public BankingManagerPage acceptAlert() {
        driver.switchTo().alert().accept();
        return this;
    }

    public BankingManagerPage sortByFirstNameAsc() {
        waitElementToBeClickable(firstNameColumnInTable);
        firstNameColumnInTable.click();
        firstNameColumnInTable.click();
        return this;
    }

    public Boolean customerExistsInTable(String name, String surname, String postCode) {
        String compositKey = makeCompositeKey(name, surname, postCode);

        var sj = new StringJoiner("\n");
        customersTable.forEach(element -> sj.add(element.getText()));
        var s = sj.toString();

        Map<String, String> map = Arrays.stream(s.split("\n"))
                .collect(Collectors.toMap(kv -> Arrays.stream(kv.split(" "))
                                .limit(3)
                                .collect(Collectors.joining("-")),
                        Function.identity()));

        return map.containsKey(compositKey);
    }

    public Boolean checkSortAsc(String firstName) {
        return firstNameInSortedTable.getText().equals(firstName);
    }

    public List<String> deleteCustomer() {
        waitElementToBeClickable(deleteCustomer);

        Map<String, List<WebElement>> nameToRowMap = new HashMap<>();
        for (var row : customersTable) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            var nameCell = cells.get(0);
            nameToRowMap.putIfAbsent(nameCell.getText(), cells);
        }

        Map<String, Integer> nameToSizeMap = new HashMap<>();
        nameToRowMap.keySet().forEach(key -> nameToSizeMap.put(key, key.length()));

        double avgNameSize = nameToSizeMap.values().stream().collect(Collectors.averagingInt(num -> num));
        var targetName = findTargetName(nameToSizeMap, avgNameSize);

        List<WebElement> targetCells = nameToRowMap.get(targetName);
        var deletedCustomer = targetCells.stream().limit(3).map(WebElement::getText).toList();

        var deleteButton = targetCells.get(targetCells.size() - 1).findElement(By.tagName("button"));
        deleteButton.click();

        return deletedCustomer;
    }

    private String makeCompositeKey(String name, String surname, String postCode) {
        return "%s-%s-%s".formatted(name, surname, postCode);
    }

    private String findTargetName(Map<String, Integer> nameToSizeMap, double avgNameSize) {
        double minDiff = Double.MAX_VALUE;
        String targetName = "";
        for (var entry : nameToSizeMap.entrySet()) {
            var nameLength = entry.getValue();
            double diff = Math.abs(avgNameSize - nameLength);
            if (minDiff > diff) {
                minDiff = diff;
                targetName = entry.getKey();
            }
        }
        return targetName;
    }
}
