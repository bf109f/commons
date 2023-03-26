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
                //菜单测试
                testPage(driver, parentLi);
            }
        } catch (Exception e) {

        } finally {
            driver.quit();
        }
    }

    /**
     * 获取驱动
     *
     * @return 驱动
     */
    public WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }

    /**
     * 登录
     *
     * @param driver 驱动
     * @param url    登录地址
     */
    public void login(WebDriver driver, String url) {
        driver.get(url);
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("zhanggaoqiang");

        driver.findElement(By.name("password")).sendKeys("abc123");

        driver.findElement(By.id("btnLogin")).click();
    }

    /**
     * 页面测试
     *
     * @param driver   驱动
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

    /**
     * 菜单测试
     *
     * @param driver 驱动
     * @param menuLi 页面元素
     */
    public void testMenu(WebDriver driver, WebElement menuLi) {
        //点击菜单
        menuLi.click();
        //获取页面
        WebElement mainContentPage =
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.className("wrapper")));
        //

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }*/
        WebElement tabContent =
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.cssSelector(".tmv-customize")));
//        mainContentPage.findElement(By.cssSelector(".tmv-customize"));
        //多tab页面
        if (tabContent != null) {
            List<WebElement> tabs = tabContent.findElements(By.tagName("li"));
            if (tabs != null && tabs.size() > 0) {
                for (WebElement tab : tabs) {
                    testTab(driver, tab);
                }
            }
        }
        //无tab页面
        else {

        }
    }

    /**
     * 多tab页面测试
     *
     * @param driver 驱动
     * @param tab    tab
     */
    public void testTab(WebDriver driver, WebElement tab) {
        tab.click();
        testSearch(driver);
    }

    /**
     * list页面表单查询
     *
     * @param driver 驱动
     */
    public void testSearch(WebDriver driver) {
        //页面主体
        WebElement panelBody =
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.className("panel-body")));
        WebElement form = panelBody.findElement(By.tagName("form"));
        //页面输入框
        List<WebElement> inputs = form.findElements(By.tagName("input"));
        for (WebElement formInput : inputs) {
            if (formInput.isDisplayed()) {
                formInput.sendKeys(formInput.getAttribute("id"));
            }
        }
        //页面下拉列表
        WebElement selects = form.findElement(By.tagName("select"));

    }

    @Test
    public void testSingle() {
        WebDriver driver = getDriver();
        login(driver, "");
        WebElement menu =
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(page -> page.findElement(By.className("sidebar-sub-menu")));
        //根菜单
        List<WebElement> rootLis = menu.findElements(By.className("dcjq-parent-li"));
        for (WebElement rootLi : rootLis) {
            if (rootLi.getText().equals("项目报表")) {
                rootLi.click();
                testMenu(driver, rootLi.findElement(By.tagName("li")));
            }
        }
    }
}
