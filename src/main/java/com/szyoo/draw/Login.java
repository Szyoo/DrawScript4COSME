package com.szyoo.draw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    /**
     * 登陆流程，需要时调用
     * 
     * @param driver
     * @return boolean 完成后返回true
     */
    public static boolean login(WebDriver driver) {
        int successCount = 0;
        for (int i = 0; i < 5; i++) {
            try {
                driver.findElement(By.className("em-placeholder")).sendKeys("losiner.y@gmail.com");
                successCount++;
            } catch (Exception e) {
            }
            try {
                driver.findElement(By.xpath("//input[@type='password']")).sendKeys("ysz960411");
                successCount++;
            } catch (Exception e) {
            }
            try {
                driver.findElement(By.xpath("//input[@class='button-submit']")).click();
                if (++successCount >= 2) {
                    return true;
                } else {
                    successCount = 0;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }
}
