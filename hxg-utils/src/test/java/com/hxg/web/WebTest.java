package com.hxg.web;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebTest {
    @Test
    public void test() {

        WebDriver driver = getDriver();
        login(driver, "");

        //等待
        /*WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));*/
        WebElement menu =
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.className("sidebar-sub-menu")));
        //根菜单
        List<WebElement> rootLis = menu.findElements(By.className("dcjq-parent-li"));

        try {
            for (WebElement parentLi : rootLis) {
                testPage(driver, parentLi);

                testMenu(driver, rootLis.get(1));
                for (WebElement webElement : rootLis) {
                    if ("招聘邀约记录".equals(webElement.getText())) {

                    }
                }
            }
        } catch (Exception e) {

        }
        finally {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }

    public void login(WebDriver driver, String url) {
        driver.get(url);
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("zhanggaoqiang");

        driver.findElement(By.name("password")).sendKeys("abc123");

        driver.findElement(By.id("btnLogin")).click();
    }

    /**
     * 页面测试
     * @param driver 驱动
     * @param parentLi 父菜单
     */
    public void testPage(WebDriver driver, WebElement parentLi) {
        WebElement li = parentLi.findElement(By.tagName("li"));
        //判断是否有子菜单
        List<WebElement> childLis = li.findElements(By.tagName("li"));
        if (childLis == null || childLis.size() == 0) {
            if (!li.isDisplayed()) {
                parentLi.click();
            }
            testMenu(driver, li);
            return;
        }

        for (WebElement childLi : childLis) {
            //元素不可见时会报错
            if (!childLi.isDisplayed()) {
                li.click();
                testPage(driver, childLi);
            }
        }
    }

    public void testMenu(WebDriver driver, WebElement webElement) {
        //点击菜单
        webElement.click();
        //获取页面
        WebElement mainContentPage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.className("wrapper")));
        //判断页面上是否有tab
        for (WebElement tab : mainContentPage.findElements(By.className("tmv-customize"))) {
            testTab(driver, tab);
        }
    }

    public void testTab(WebDriver driver, WebElement tab) {
        //点击tab
        WebElement li = tab.findElement(By.tagName("li"));
        if (li == null) {
            return;
        }
        li.click();

        testSearch(driver);
    }

    public void testSearch(WebDriver driver) {
        //页面主体
        WebElement panelBody =
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.className("panel-body")));
        List<WebElement> formInputs = panelBody.findElement(By.tagName("form")).findElements(By.tagName("input"));
        for (WebElement formInput : formInputs) {
            if (formInput.isDisplayed()) {
                formInput.sendKeys(formInput.getAttribute("id"));
            }
        }
    }

    @Test
    public void testSingle() {

    }
}
