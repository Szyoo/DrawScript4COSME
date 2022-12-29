package com.szyoo.draw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.szyoo.Driver;

public class FindByCSS {

    private static String[] urlInfoPageA = {
            "div.block-info>div.inner>div.box-present>div.col-wrap div.col>div.txtset div.btn a.gatrack", // 图文内嵌按钮
            "div.block-info + div.box-image>a.gatrack", // 图文内嵌按钮，但按钮单独在下一个兄弟元素
            "div.bg-area div.present li.btn-cosme>a.gatrack", // 特殊情况，在介绍界面就有多品牌可选
            "#main>#section>.content>.apply>input.btn-green", // produce member，类型为input
            "#presentInfo>div.detail>p.apply>a", // brand collection
            "#brd-psnt>div.brd-psnt-frm>div.brd-psnt-frm-inr>a",// brand fanclub
    };
    private static String[] urlInfoPageImg = {
            "div.block-info>div.inner>div.box-present>div.col-wrap div.col>p img[src]", // 图文内嵌图片
            "div.box-image>div.clearfix>div.fl-none>img", // 图文内嵌图片，但图片单独在上一个兄弟元素
            "div.bg-area div.present>img", // 特殊情况，在介绍界面就有多品牌可选
            "#main>#section>.photo>img", // produce member
            "#presentInfo>p.img>img", // brand collection
            "#brd-psnt>div.brd-psnt-frm>div.brd-psnt-frm-inr>a img",// brand fanclub
    };
    private static String[] urlLoginFrame = {
            "div.inr>div.TB_main .btn-cmn a", // 点击produce member时弹出的登录框按钮
            "div.usr-auth>ul>li:nth-child(2)>a",//主界面下的登陆按钮
    };

    // 登陆界面邮箱密码输入框，以及提交按钮
    private static String urlMail = "form>ul input#LoginUserLoginId";
    private static String urlPassword = "form>ul input#LoginUserPassword";
    private static String urlLoginSubmit = "form>input.button-submit";

    // produce member応募済み灰色按钮
    private static String urlProduceMemberApplyAfter = "#main>#section>.content>p.apply-after";

    // 个人信息界面次へ進む按钮
    private static String urlPersonalInfoConfirm = "#main form>p.enquete-apl-btn>input.btn-green";

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
            return null;
        }
    }

    /**
     * 通过Css查找抽奖介绍界面的开始抽奖按钮，成功返回<a>元素对象，失败返回null
     */
    public static WebElement InfoPageA() {
        WebElement element = null;
        for (String url : urlInfoPageA) {
            element = findByCss(url);
            if (element != null) {
                break;
            }
        }
        if (element == null) {
            System.out.print(" 抽奖介绍界面的开始抽奖按钮未找到 ");
            System.out.println(Driver.driver.getCurrentUrl());
        }
        return element;
    }

    /**
     * 通过Css查找抽奖介绍界面的图片，成功返回<img>元素对象，失败返回null
     */
    public static WebElement InfoPageImg() {
        WebElement element = null;
        for (String url : urlInfoPageImg) {
            element = findByCss(url);
            if (element != null) {
                break;
            }
        }
        if (element == null) {
            System.out.print(" 抽奖介绍界面的图片未找到 ");
            System.out.println(Driver.driver.getCurrentUrl());
        }
        return element;
    }

    /**
     * 通过Css查找登陆弹出框，成功返回登录按钮<a>元素对象，失败返回null
     */
    public static WebElement LoginFrame() {
        WebElement element = null;
        for (String url : urlLoginFrame) {
            element = findByCss(url);
            if (element != null) {
                break;
            }
        }
        return element;
    }

    /**
     * 通过Css查找登陆页面邮箱输入，成功返回登录按钮<input>元素对象，失败返回null
     */
    public static WebElement LoginMail() {
        WebElement element = findByCss(urlMail);
        return element;
    }

    /**
     * 通过Css查找登陆页面密码输入，成功返回登录按钮<input>元素对象，失败返回null
     */
    public static WebElement LoginPassword() {
        WebElement element = findByCss(urlPassword);
        return element;
    }

    /**
     * 通过Css查找登陆页面登陆按钮，成功返回登录按钮<input>元素对象，失败返回null
     */
    public static WebElement LoginSubmit() {
        WebElement element = findByCss(urlLoginSubmit);
        return element;
    }

    /**
     * 通过Css查找ProduceMember応募済み灰色按钮，成功返回True，失败返回False
     */
    public static Boolean ProduceMemberApplyAfter() {
        WebElement element = findByCss(urlProduceMemberApplyAfter);
        if (element == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 通过Css查找个人信息界面次へ進む按钮，成功返回按钮<input>元素对象，失败返回null
     */
    public static WebElement PersonalInfoConfirm() {
        WebElement element = findByCss(urlPersonalInfoConfirm);
        return element;
    }
}
