package com.szyoo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

import com.szyoo.draw.Draw;
import com.szyoo.draw.Find;

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
    void countBtn() {
        WebDriver driver = Driver.setDriverChrome();
        List<Present> presents = Find.searchPresentToList();
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
            if (Find.findDrawBtn() != null) {
                System.out.println("Gotcha!");
                success++;
            } else if (Find.findDrew()) {
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
