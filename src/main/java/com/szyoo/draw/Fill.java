package com.szyoo.draw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.szyoo.InputController;
import com.szyoo.Present;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Fill {
    private static List<String> keywords = Arrays.asList("クレンジング料", "化粧水", "乳液", "クリーム", "アイクリーム・ジェル", "デパート・百貨店",
            "化粧品専門店", "ドラッグストア", "乾燥肌", "ウォーター・ミスト", "ボディクリーム", "保湿", "乾燥を防ぐ", "興味がある", "うるおい", "低刺激", "とても満足",
            "使ったことはないが、よく知っている", "＠ｃｏｓｍｅ", "SNS", "商品を購入してみたい", "テスターを使ってみたい", "使用したことはないが", "を与える", "透明感", "嬉しい",
            "わからない", "00円以上", "応募にあたり、選択したブランドを「お気に入り登録」します", "非常に傷んでいる", "シャンプー", "洗い流すトリートメント（ポンプタイプ）",
            "リンス・コンディショナー", "仕上がり", "気になることがある", "アイブロウ", "アイライナー", "アイシャドウ", "チーク", "口紅", "長さが出る", "ボリューム", "自然",
            "セミロング", "香りが好き", "乾燥", "ニキビ", "しっかり落としたい", "新しい商品はすぐに試してみたい", "試すのが好きだ", "信頼できるメーカーやブランド", "ダメージをケアする",
            "洗い流すトリートメント", "美容成分配合である", "魅力的だ", "ペンシルタイプ", "リキッドタイプ", "細くも太くも描ける", "描きやすい", "ボディソープ（泡で出てくるタイプ）", "手洗い",
            "スポンジ", "水や汗に強い", "白くならない", "日焼けしない", "肌に負担のない", "肌に優しい", "1回程度", "ネイルカラー", "やや敏感", "施術を受けたことはないし、興味もない",
            "ときどきある", "冷たいものがしみる", "ムシ歯になりやすい", "肌がしっとりする", "顔や体に「日焼け止め」を使用", "標準色", "化粧のりがよい", "くずれにくい", "週に",
            "自宅", "大切だと思う", "日差しの強い季節", "歯ブラシ", "上記を確認の上、応募する", "うるおう", "全てのブランドをお気に入り登録して応募", "言葉だけは知っている",
            "日焼け止めを使用している", "スキンケア", "「白髪」は全くない", "満足している", "応募にあたり");

    /**
     * 填表完成后调用，点击送信按钮并判断处理送信结果
     * 
     * @param driver
     * @param present
     */
    public static Boolean send(WebDriver driver, Present present) {
        try {
            System.out.print("填写完成..");
            Find.findSendBtn().click();
            if (checkSend(driver)) {
                if (Find.findOvertime()) {
                    return false;
                } else {
                present.setDrew(true);
                present.setDrawDate();
                Present.countDraw();
                System.out.println("送信成功..记录并开始下一个抽奖");   
                return true;
                }
            } else {
                // 送信失败，可能是有必填项目未填写，手动填写后在控制台确认
                InputController.waitRefill();
                send(driver, present);
            }
        } catch (Exception e) {
            System.out.println("送信按钮查找失败");
        }
        return false;
    }

    private static Boolean checkSend(WebDriver driver) {
        if (Find.findDrew()) {
            return true;
        }else if(Find.findSendBtn() == null) {
            try {
                Find.findDrawBtn(); // 回到了最初品牌界面说明已经抽完
                return true;
            } catch (Exception e1) {
            }
        }
        return false;
    }

    /**
     * 抽奖流程中调用，自动完成填写名字前的填表流程
     * 
     * @param driver
     * @return boolean 完成后返回true
     */
    public static boolean fillQuestion(WebDriver driver, Present present) {
        String[] xpath = { "//td/span/label", "//div[@class='qa']/div/label" };
        List<WebElement> list = new ArrayList<WebElement>();
        System.out.print("开始填写表格..");
        for (String string : xpath) {
            list = driver.findElements(By.xpath(string));
            for (WebElement label : list) {
                for (String key : keywords) {
                    // 若Label中存在指定关键词则点击其下属的input
                    if (label.getText().contains(key)) {
                        try {
                            label.findElement(By.cssSelector("input")).click();
                        } catch (Exception e) {
                            System.out.println(label.getText() + " 点击失败");
                        }
                    }
                }
            }
            if (list.size() > 0) {
                break;
            }
        }
        try {
            // 定位商品选择文字td的父元素tr的下一个，即商品选择所在tr
            WebElement itemChoiceTR = driver
                    .findElement(By.xpath("//td[contains(.,'ご希望の')]/../following-sibling::tr[1]"));
            // 定位所有input选择框
            List<WebElement> itemList = itemChoiceTR.findElements(By.cssSelector("td>span>label>input"));
            System.out.println("\n存在多个奖品可选择，奖品链接如下：");
            System.out.println(present.getLink());
            System.out.println("\n请查看后输入对应编号选择奖品");

            List<String> content = new ArrayList<String>();
            List<Integer> choiceNum = new ArrayList<Integer>();
            for (WebElement w : itemList) {
                String str = w.findElement(By.xpath("./..")).getText();
                content.add(str);
                choiceNum.add(content.indexOf(str) + 1);
                // 打印No.编号（input中的value值）
                System.out.println("No." + (content.indexOf(str) + 1) + " :" + str);
            }
            System.out.print(">  No.");
            int num = InputController.chooseItem(choiceNum);
            for (WebElement w : itemList) {
                if (w.findElement(By.xpath("./..")).getText().equals(content.get(num - 1))) {
                    w.click();
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    /**
     * 填表流程中调用，自动填写个人信息相关信息
     * 
     * @param driver
     * @return boolean 完成后返回true
     */
    public static boolean fillName(WebDriver driver) {
        String[] str = { "名前", "王韻雅", "年齢", "25" };
        for (int i = 0; i < str.length; i += 2) {
            try {
                // 定位指定文字td的下一个td，即input所在td
                WebElement inputTd = driver
                        .findElement(By.xpath("//td[contains(.,'" + str[i] + "')]/following-sibling::td[1]"));
                // 定位到该td下的input
                WebElement input = inputTd.findElement(By.cssSelector("td>span>input"));
                input.clear();
                input.sendKeys(str[i + 1]);
            } catch (Exception e) {
                if (!driver.getCurrentUrl().contains("cosme.net/brand")) {
                    System.out.println("查找" + str[i] + "输入框失败");
                }
                return false;
            }
        }
        try {
            Select select = new Select(driver.findElement(By.cssSelector("select[name=prof_010_job1]")));
            select.selectByVisibleText("自営業/自由業");
        } catch (Exception e) {
            try {
                Select select = new Select(driver.findElement(By.cssSelector("select[name=prof_010_job1]")));
                select.selectByVisibleText("自営業・自由業");
            } catch (Exception e1) {
                System.out.println("查找职业输入框失败");
                return false;
            }
        }
        return true;
    }
}
