package com.szyoo.draw;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    /**
     * 设置Driver路径，并代入默认option参数启动Chrome
     * 
     * @return WebDriver
     */

    public static WebDriver driver = Driver.setDriverChrome();
    // WebDriver driver = Driver.setDriverEdge();
    // WebDriver driver = Driver.setDriverFirefox();

    public static WebDriver setDriverChrome() {
        try {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();

            // 小雅电脑配置
            // options.addArguments("--user-data-dir=C:\\Users/student/AppData/Local/Google/Chrome/User Data");
            // options.addArguments("--profile-directory=Profile 6");

            // 远程桌面配置
            // options.addArguments("--user-data-dir=C:\\Users/losin/AppData/Local/Google/Chrome/User Data");
            // options.addArguments("--profile-directory=Default");
            // options.addArguments("--no-sandbox");

            return new ChromeDriver(options);
        } catch (Exception e) {
            System.out.println("驱动加载失败，请关闭当前所有已开启的Chrome浏览器后重新运行程序");
            System.exit(0);
        }
        return null;
    }

    /**
     * 设置Driver路径，并启动Edge
     * 
     * @return WebDriver
     */
    public static WebDriver setDriverEdge() {
        try {
            System.setProperty("webdriver.edge.driver", "driver/msedgedriver.exe");
            return new EdgeDriver();
        } catch (Exception e) {
            System.out.println("驱动加载失败，请关闭当前所有已开启的Edge浏览器后重新运行程序");
            System.exit(0);
        }
        return null;
    }

    public static WebDriver setDriverFirefox() {
        try {
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
            // FirefoxDriver fd = new FirefoxDriver();
            // System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "false");
            return new FirefoxDriver();
        } catch (Exception e) {
            System.out.println("驱动加载失败，请关闭当前所有已开启的Edge浏览器后重新运行程序");
            System.exit(0);
        }
        return null;
    }

    /**
     * 关闭当前窗口以外的所有窗口
     * 
     * @param driver
     */
    public static void closeOtherWindow(WebDriver driver) {
        String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
        Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
        for (String w : handles) {
            if (!w.equals(currentWindow)) {// 获取到的窗口句柄不同于初始窗口
                driver.switchTo().window(w);// 切换到该窗口句柄
                driver.close();// 关闭当前切换到的窗口
            }
        }
        driver.switchTo().window(currentWindow);
    }

    /**
     * 切换到当前能识别的下一个窗口
     * 
     * @param driver
     */
    public static void switchNextWindow(WebDriver driver) {
        // 获取所有窗口句柄
        String handle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String window : handles) {
            if (!window.equals(handle)) {
                // 关闭当前窗口
                driver.close();
                // 切换到下一个窗口句柄
                driver.switchTo().window(window);
            }
        }
        // String handle = driver.getWindowHandle();
        // Set<String> winHandels = driver.getWindowHandles(); // 得到当前窗口的set集合
        // List<String> it = new ArrayList<String>(winHandels); // 将set集合存入list对象
        // if (it.size() > 1 && (!it.get(1).equals(handle))) {
        // driver.switchTo().window(it.get(1)); // 切换到弹出的新窗口
        // }
    }
}
