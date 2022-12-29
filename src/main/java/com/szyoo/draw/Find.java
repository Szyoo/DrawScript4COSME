package com.szyoo.draw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.szyoo.Driver;
import com.szyoo.Present;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Find {
    private static List<String> xpath_draw = Arrays.asList("btn-green", "//p[contains(text(),'すぐ応募')]",
            "//p[contains(.,'応募する')]", "//p[contains(.,'応募する')]", "//a[contains(.,'すぐ応募')]",
            "//*[contains(@class,'article-item box-image banner')]/a/img[contains(@src,'bt4.png')]", "//a[contains(.,'PCから応募')]",
            "//a[text() = 'こちら' and contains(@rel,'nofollow')]",
            "//a[contains(.,'こちら') and contains(@rel,'nofollow')]",
            "//img[contains(@src,'037_123_original_pc_bt4.png')]", "//span[contains(text(),'応募する')]","//input[@value='応募する' and @type='button']");
    private static List<String> xpath_drew = Arrays.asList("//*[contains(text(),'応募済み')]",
            "//*[contains(text(),'すでに回答済')]", "//*[contains(.,'すでに回答済')]", "//*[@class='apply-after']","//h4[@class='thanks'and contains(text(),'ご応募ありがとう')]");

    private static List<String> xpath_toFill = Arrays.asList("//input[@value='次へ進む']",
            "//p[@class='present-apl-btn']/input[@value='応募する']","//input[contains(@value,'上記の内容で応募する') and @type='submit']");
            
    private static List<String> xpath_send = Arrays.asList("//input[@alt='送信' and contains(@src,'def_pc_1')]",
            "//input[contains(@value,'回答して応募する') and @type='submit']");

    /**
     * 在已打开页面上查找所有可用奖品信息，并将[链接，文本，当前时间]汇总为Present的List
     * 
     * @return
     */
    public static List<Present> searchPresentToList() {
        System.out.println("开始识别奖品...\n请稍等...");
        Driver.driver.get("https://www.cosme.net/present");
        Driver.closeOtherWindow(Driver.driver);

        List<WebElement> presents_special = Driver.driver.findElements(By.cssSelector("a[href*='present/detail/present_id']"));
        List<WebElement> presents_normal = Driver.driver.findElements(By.cssSelector("a[href*='/as.iy.impact-ad.jp/ct?id=']"));

        List<Present> presents = new ArrayList<Present>();

        Present.addElement(presents_special, presents);
        Present.addElement(presents_normal, presents);

        Driver.driver.get("https://www.cosme.net/brandfanclub/present");
        Driver.closeOtherWindow(Driver.driver);

        List<WebElement> presents_brand = Driver.driver.findElements(
                By.cssSelector("div[class=psnt]>ul[class=clearfix]>li>a[href*='cosme.net/brand/brand_id']"));
        Present.addElement(presents_brand, presents);
        System.out.println("识别完成！共识别到奖品：" + Present.getCall() + " 个");
        return presents;
    }

    /**
     * 通过Xpath查找元素，成功返回元素对象，失败返回null
     * 
     * @param xpath路径
     */
    public static WebElement findByXpath(String xpath) {
        WebElement btn = null;
        try {
            btn = Driver.driver.findElement(By.xpath(xpath));
            return btn;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 在奖品一级介绍界面查找募集按钮，找到了返回WebElement，未找到则返回值为null的WebElement
     * 
     * @return WebElement 按钮对象或者Null
     * 
     */
    public static WebElement findDrawBtn() {
        WebElement btn = null;
        for (String xpath : xpath_draw) {
            btn = findByXpath(xpath);
            if (btn != null) {
                return btn;
            } 
        }
        return null;
    }

    /**
     * 在点击募集后进入的个人信息确认界面查找确认募集按钮，找到了返回WebElement，未找到则返回值为null的WebElement
     * 
     * @return WebElement 按钮对象或者Null
     * 
     */
    public static WebElement findToFillBtn() {
        WebElement btn;
        for (String xpath : xpath_toFill) {
            btn = findByXpath(xpath);
            if (btn != null) {
                return btn;
            }
        }
        return null;
    }

    /**
     * 查找已募集按钮或者已募集标签
     * 
     * @return 找到了返回true，未找到则返回false
     */
    public static Boolean findDrew() {
        WebElement btn = null;
        for (String xpath : xpath_drew) {
            btn = findByXpath(xpath);
            if (btn != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找超时标签
     * 
     * @return 找到了返回true，未找到则返回false
     */
    public static Boolean findOvertime() {
        WebElement btn = findByXpath("回答時間エラー");
        if (btn != null) {
            return true;
        }
        return false;
    }

    /**
     * 查找送信按钮
     * 
     * @return 找到了返回true，未找到则返回false
     */
    public static WebElement findSendBtn() {
        WebElement btn = null;
        for (String xpath : xpath_send) {
            btn = findByXpath(xpath);
            if (btn != null) {
                return btn;
            }
        }
        return null;
    }

    public static boolean findFillFailed() {
        return false;
    }

}
