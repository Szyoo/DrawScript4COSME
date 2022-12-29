package com.szyoo.draw;

import java.util.List;

import com.szyoo.Driver;
// import com.szyoo.InputController;
import com.szyoo.Present;

public class Draw {
    /**
     * 整体抽奖流程控制，包括遍历奖品链接以及单个完整抽奖
     * 
     * @param presents
     */
    public static void drawAll( List<Present> presents) {
        int presentsSize = presents.size();
        int count = 0;
        System.out.println("开始抽奖啦，祝你好运！~~");
        for (Present p : presents) {
            count++;
            if (!p.getDrew()) { // 未过抽奖
                System.out.print("当前进度: " + count + "/" + presentsSize);
                Driver.driver.get(p.getLink()); // 跳转奖品介绍界面
                if (!Draw.drawOnce(p)) {
                    break;
                }
            } else {
                // System.out.println(" 存档记录已抽取，跳过");
            }
        }
    }

    /**
     * 打开奖品介绍界面后，查找并点击募集按钮，点击进入填表，填写所有信息，点击送信
     * 
     * @param Driver.driver  Web驱动
     * @param present Present对象
     * @return ContinueFlag 根据用户输入需要终止全部抽奖流程时返回false
     */
    public static Boolean drawOnce( Present present) {
        // 尝试进入填表页面，若失败则跳过
        if (gotoFill(present)) {
            System.out.print(" 开始抽取..");
            Fill.fillQuestion(Driver.driver, present);
            Fill.fillName(Driver.driver);
            if (Fill.send(Driver.driver, present) == false) { // 出现超时，重置流程
                drawOnce(present);
            }
        }
        return true;
    }

    /**
     * 单个奖品抽奖流程开始后调用，尝试处理登录等特殊情况并到达填表界面
     * 
     * @param Driver.driver
     * @param present
     * @return 无法确认/已募集的情况下需要跳过，返回false，正常情况下返回true
     */
    public static boolean gotoFill( Present present) {
        int failCount = 0;
        boolean onclickFlag1 = false;
        boolean onclickFlag2 = false;
        do {
            // 十次循环内始终保证当前处于正确窗口
            Driver.switchNextWindow(Driver.driver);
            if (Driver.driver.getTitle().contains("ログイン／メンバー登録")) {
                // 如果需要登录则进行登录流程
                Login.login(Driver.driver);
            } else {
                try {
                    Find.findByXpath("//a[contains(text(),'ログイン')]").click();
                    Login.login(Driver.driver);
                } catch (Exception e) {
                }
            }
            try {
                // 尝试在找到募集按钮的情况下点击
                Find.findDrawBtn().click();
                Thread.sleep(2000);
                onclickFlag2 = true;
                continue;
            } catch (Exception e) {
            }
            try {
                // 尝试在找到确认个人信息按钮的情况下点击
                Find.findToFillBtn().click();
                Thread.sleep(4000);
                onclickFlag1 = true;
            } catch (Exception e) {
            }
            if (Find.findDrew()) {
                // 已募集的情况下终止本轮抽奖
                present.setDrew(true);
                present.setDrawDate();
                Present.countDraw();
                if (onclickFlag1 && onclickFlag2) {
                    System.out.println(" 抽取成功，记录并开始下一个抽奖");
                } else {
                    System.out.println(" 检测到已抽取，记录并跳过");
                }
                return false;
            } else if (!(Find.findSendBtn() == null)) {
                // 找到填表界面内的送信按钮时返回true
                return true;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (++failCount < 10);
        System.out.println("奖品状态无法确认，尝试跳过！\n当前奖品信息：" + present.toString());
        return false;
    }
}
