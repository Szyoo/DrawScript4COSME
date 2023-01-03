package com.szyoo.find;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.szyoo.draw.Driver;

public class ByXpath {

    private static String[] xpath_drew = {
            "//td[contains(.,'すでに回答済')]",
            "//h4[@class='thanks'and contains(text(),'ご応募ありがとう')]"
    };

    private static String xpath_overtime = "//td[contains(.,'回答時間エラー')]";

    /**
     * 通过Xpath查找元素，成功返回元素对象，失败返回null
     * 
     * @param xpath路径
     */
    public static WebElement find(String xpath) {
        WebElement element = null;
        try {
            element = Driver.driver.findElement(By.xpath(xpath));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查找已募集按钮或者已募集标签
     * 
     * @return 找到了返回true，未找到则返回false
     */
    public static Boolean Drew() {
        WebElement btn = null;
        for (String xpath : xpath_drew) {
            btn = find(xpath);
            if (btn != null) {
                return true;
            }
        }
        if (ByCss.ProduceMemberApplyAfter()) {

        }
        return false;
    }

    /**
     * 查找超时标签
     * 
     * @return 找到了返回true，未找到则返回false
     */
    public static Boolean Overtime() {
        WebElement btn = find(xpath_overtime);
        if (btn != null) {
            return true;
        }
        return false;
    }
}
