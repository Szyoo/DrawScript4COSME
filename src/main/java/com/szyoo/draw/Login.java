package com.szyoo.draw;

import org.openqa.selenium.WebElement;

import com.szyoo.Driver;

public class Login {
    private static String user_mail_y = "losiner.y@gmail.com";
    private static String user_pass_y = "ysz960411";
    private static String user_mail_w = "wcatfish0606@gmail.com";
    private static String user_pass_w = "wangyunya0606";

    public static Boolean loginStatus = false;

    /**
     * 登陆流程，需要时调用
     * 
     * @param driver
     * @return boolean 完成后返回true，失败返回false
     */
    private static boolean login() {
        String currentUserMail = user_mail_y;
        String currentUserPass = user_pass_y;

        WebElement mail = FindByCSS.LoginMail();
        WebElement password = FindByCSS.LoginPassword();
        WebElement submit = FindByCSS.LoginSubmit();

        if (mail != null && mail.getAttribute("value") != currentUserMail) {
            mail.sendKeys(currentUserMail);
            if (password != null && password.getAttribute("value") != currentUserPass) {
                mail.sendKeys(currentUserPass);
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
        WebElement loginButton = FindByCSS.LoginFrame();
        if (loginButton != null) {
            loginButton.click();
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (Driver.driver.getTitle().contains("ログイン／メンバー登録")) {
            login();
        }
    }
}
