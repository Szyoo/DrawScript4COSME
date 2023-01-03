package com.szyoo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

import com.szyoo.draw.Draw;
import com.szyoo.draw.Driver;
import com.szyoo.entity.Present;
import com.szyoo.find.ByCss;
import com.szyoo.find.ByXpath;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     * 
     * @throws IOException
     */
    @Test
    void testApp() throws IOException {
        WebDriver driver = Driver.setDriverChrome();
        String link = "https://www.cosme.net/brand/brand_id/1705/top";

        driver.get(link);
        Driver.closeOtherWindow(driver);

        Present p = new Present();
        p.setLink(link);

        Draw.drawOnce(p);

    }

    @Test
    void findDrawbyCSSAll() {
        int success = 0;
        int failed = 0;
        List<Present> list = Present.searchPresentToList();
        for (Present p : list) {
            Driver.driver.get(p.getLink()); // 跳转奖品介绍界面
            if (ByCss.InfoPageA() != null) {
                System.out.print("ok1 ");
                if (ByCss.InfoPageImg() != null) {
                    System.out.print("ok2 ");
                    success++;
                } else {
                    failed++;
                }
            } else {
                failed++;
            }
        }
        System.out.println("失败" + failed + "，成功" + success);
    }

    @Test
    void findDrawbyCSS() {
        Driver.driver.get("https://www.cosme.net/brand/brand_id/353/tieup/00353202212-01/page/page.html");
        if (ByCss.InfoPageA() != null) {
            System.out.print("ok1 ");
        }
        if (ByCss.InfoPageImg() != null) {
            System.out.print("ok2 ");
        }
    }

    @Test
    void countBtn() {
        WebDriver driver = Driver.setDriverChrome();
        List<Present> presents = Present.searchPresentToList();
        countDrawBtn(presents, driver);
        Present.showCall();
    }

    /**
     * 遍历传入Present对象的List的对应页面后统计找到的募集按钮数量
     * 
     * @param list
     * @param driver
     */
    private void countDrawBtn(List<Present> list, WebDriver driver) {
        int success = 0;
        int fail = 0;
        int drew = 0;

        for (Present p : list) {
            driver.get(p.getLink());// 进入对应奖品的介绍界面
            if (ByCss.InfoPageA() != null) {
                System.out.println("Gotcha!");
                success++;
            } else if (ByXpath.Drew()) {
                System.out.println("Already Drew!");
                drew++;
            } else {
                System.out.println("/@Test: Draw Btn not Found!\n" + p.toString());
                fail++;
            }
        }
        System.out.println("Result [fail=" + fail + ", sucess=" + success + ", drew=" + drew + ", total:"
                + (success + fail + drew) + "]");
    }
}
