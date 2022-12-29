package com.szyoo.draw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.szyoo.Driver;

public class FindByCSS {

    /**
     * 通过CssSelector查找元素，成功返回元素对象，失败返回null
     * 
     * @param css路径
     */
    private static WebElement findByCss(String css) {
        WebElement element = null;
        try {
            element = Driver.driver.findElement(By.cssSelector(css));
            return element;
        } catch (Exception e) {
            System.out.print(" element未找到 ");
            return null;
        }
    }

    /**
     * 通过Css查找抽奖介绍界面的开始抽奖按钮，成功返回<a>元素对象，失败返回null
     * 
     * @param css路径
     */
    public static WebElement InfoPageA() {
        String css1 = "div#block-info>div#inner>div#box-present>div#col-wrap div#col>div#txtset div#btn a#gatrack";
        String css2 = "div#block-info + div#box-image>a#gatrack";
        WebElement element = findByCss(css1);
        if (element == null) {
            findByCss(css2);
        } else {
            System.out.print (" 抽奖介绍界面的开始抽奖按钮未找到 ");
            System.out.println(Driver.driver.getCurrentUrl());
        }
        return element;
    }

    /**
     * 通过Css查找抽奖介绍界面的图片，成功返回<img>元素对象，失败返回null
     * 
     * @param css路径
     */
    public static WebElement InfoPageImg() {
        String css1 = "div#block-info>div#inner>div#box-present>div#col-wrap div#col>p img[src]";
        String css2 = "div#box-image>div#clearfix>div#fl-none>img";
        WebElement element = findByCss(css1);
        if (element == null) {
            findByCss(css2);
        } else {
            System.out.print(" 抽奖介绍界面的图片未找到 ");
            System.out.println(Driver.driver.getCurrentUrl());
        }
        return element;
    }

}
