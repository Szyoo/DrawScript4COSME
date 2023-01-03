package com.szyoo.draw;

import org.openqa.selenium.WebElement;

import com.szyoo.Main;

import com.szyoo.find.ByCss;

public class Login {
    public static Boolean loginStatus = false;

    /**
     * 登陆流程，需要时调用
     * 
     * @param driver
     * @return boolean 完成后返回true，失败返回false
     */
    private static boolean login() {
        WebElement mail = ByCss.LoginMail();
        WebElement password = ByCss.LoginPassword();
        WebElement submit = ByCss.LoginSubmit();

        if (mail != null && mail.getAttribute("value") != Main.user.getMail()) {
            mail.sendKeys(Main.user.getMail());
            if (password != null && password.getAttribute("value") != Main.user.getPassword()) {
                mail.sendKeys(Main.user.getPassword());
                if (submit != null) {
                    submit.click();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void checkLogin() {
        WebElement loginButton = ByCss.LoginFrame();
        if (loginButton != null) {
            loginButton.click();
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Driver.driver.getTitle().contains("ログイン／メンバー登録")) {
            login();
        }
    }
}
