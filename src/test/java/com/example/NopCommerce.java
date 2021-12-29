package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NopCommerce {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static String print(String color, String printer) {
        return color + printer + "\u001B[0m";
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Actions actionProvider = new Actions(driver);
        driver.manage().window().maximize();

        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.className("login-button")).click();

        if (!driver.getCurrentUrl().contentEquals("https://admin-demo.nopcommerce.com/admin/")) {
            System.out.println(print(ANSI_RED, "system not navigate to admin dashboard"));
            driver.close();
        }
        System.out.println(print(ANSI_GREEN, "system redirect to admin page"));

        driver.findElement(By.cssSelector("ul.nav-sidebar > li.nav-item:nth-child(2)")).click();

        Assert.assertTrue(
                driver.findElement(By.cssSelector("ul.nav-sidebar > li.nav-item:nth-child(2) > ul")).isDisplayed());

        driver.findElement(By.cssSelector("ul.nav-sidebar > li.nav-item:nth-child(2) > ul > li:nth-child(1) > a"))
                .click();

        if (!driver.getCurrentUrl().contentEquals("https://admin-demo.nopcommerce.com/Admin/Product/List")) {
            System.out.println(print(ANSI_RED, "system not navigate to products list"));
            driver.close();
        }
        System.out.println(print(ANSI_GREEN, "system redirect to products list page"));

        Assert.assertEquals(driver.findElement(By.cssSelector("form h1")).getText(), "Products");

        driver.findElement(By.cssSelector(".float-right a[href=\"/Admin/Product/Create\"]")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("Product/Create"));

        if (!driver.findElement(By.cssSelector("div[data-card-name=\"product-info\"] > .card-body")).isDisplayed()) {
            driver.findElement(By.cssSelector("div[data-card-name=\"product-info\"] > .card-header")).click();
        }

        if (!driver.findElement(By.cssSelector("body")).getAttribute("class").contains("basic-settings-mode")) {
            driver.findElement(By.className("onoffswitch")).click();
        }

        driver.findElement(By.cssSelector("input[name=\"Name\"]")).sendKeys("product name");

        driver.findElement(By.id("ShortDescription")).sendKeys("product description");

        if (!driver.findElement(By.cssSelector("div[data-card-name=\"product-price\"] > .card-body")).isDisplayed()) {
            driver.findElement(By.cssSelector("div[data-card-name=\"product-price\"] > .card-header")).click();
        }
        driver.findElement(By.cssSelector("#product-price-area  input.k-formatted-value.k-input")).sendKeys("134");

        Select selectInventory = new Select(driver.findElement(By.id("ManageInventoryMethodId")));

        selectInventory.selectByVisibleText("Track inventory");

        driver.findElement(By.name("save")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("Admin/Product/List"));

        Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-success")).isDisplayed());

        driver.findElement(By.cssSelector("ul.nav-sidebar > li.nav-item:nth-child(5)")).click();

        Assert.assertTrue(
                driver.findElement(By.cssSelector("ul.nav-sidebar > li.nav-item:nth-child(5) > ul")).isDisplayed());

        driver.findElement(By.cssSelector("ul.nav-sidebar > li.nav-item:nth-child(5) > ul > li:nth-child(1) > a"))
                .click();

        if (!driver.getCurrentUrl().contentEquals("https://admin-demo.nopcommerce.com/Admin/Discount/List")) {
            System.out.println(print(ANSI_RED, "system not navigate to discounts list"));
            driver.close();
        }
        System.out.println(print(ANSI_GREEN, "system redirect to discounts list page"));

        Assert.assertEquals(driver.findElement(By.cssSelector(".content-wrapper h1")).getText(), "Discounts");

        driver.findElement(By.cssSelector("[href=\"/Admin/Discount/Create\"]")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("Admin/Discount/Create"));
        String discountName = "obaida test discount";
        driver.findElement(By.name("Name")).sendKeys(discountName);

        (new Select(driver.findElement(By.name("DiscountTypeId")))).selectByValue("2");

        driver.findElement(By.name("UsePercentage")).click();
        // driver.findElement(By.cssSelector("#pnlDiscountAmount  input.k-formatted-value.k-input")).sendKeys("134");

        Assert.assertTrue(driver.findElement(By.id("pnlDiscountPercentage")).isDisplayed());

        driver.findElement(By.cssSelector("#pnlDiscountPercentage .k-input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#pnlDiscountPercentage .k-input:nth-child(2)")).sendKeys("50");

        driver.findElement(By.id("StartDateUtc")).sendKeys("12/1/2021 12:00 AM");

        driver.findElement(By.id("EndDateUtc")).sendKeys("12/31/2021 12:00 AM");

        driver.findElement(By.name("save")).click();

        driver.findElement(By.name("SearchDiscountName")).sendKeys(discountName);

        driver.findElement(By.id("search-discounts")).click();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(5000);
        // WebElement editButton =  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#discounts-grid_wrapper table#discounts-grid tbody  tr:nth-child(1)  td:nth-child(7)  a.btn"))));
        // actionProvider.moveToElement(
        //     driver.findElement(By.cssSelector("#discounts-grid_wrapper table#discounts-grid tbody  tr:nth-child(1)  td:nth-child(7)  a.btn"))
        //     ).click().build().perform();
        // driver.findElement(By.cssSelector("#discounts-grid_wrapper table#discounts-grid tbody  tr:nth-child(1)  td:nth-child(7)  a.btn")).click();
        driver.findElement(By.xpath("//*[@id=\"discounts-grid\"]/tbody/tr[1]/td[7]/a")).click();

        if ( ! driver.findElement(By.cssSelector("[data-card-name=\"discount-applied-to-products\"] .card-body")).isDisplayed()) {
            driver.findElement(By.cssSelector("[data-card-name=\"discount-applied-to-products\"] .card-head")).click();;
        }

        driver.findElement(By.id("btnAddNewProduct")).click();

        
    }   
}
