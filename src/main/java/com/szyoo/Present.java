package com.szyoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Present {
    private String link;
    private String name;
    private boolean isDrew;
    private Date scanDate;
    private Date drawDate;
    private static int callTimes = 0;
    private static int drawTimes = 0;

    @Override
    public String toString() {
        return "Present [drawDate=" + drawDate + ", isDrew=" + isDrew + ", link=" + link + ", name=" + name
                + ", scanDate=" + scanDate + "]";
    }

    public Present() {
    }

    public Present(String link, String name, boolean isDrew, Date scanDate) {
        this.link = link;
        this.name = name;
        this.isDrew = isDrew;
        this.scanDate = scanDate;
        this.drawDate = null;
        countCall();
    }

    private static void countCall() {
        callTimes++;
    }

    public static void showCall() {
        System.out.println("Total Count: " + callTimes);
    }

    public static int getCall() {
        return callTimes;
    }

    public static void countDraw() {
        drawTimes++;
    }

    public static int showDraw() {
        return drawTimes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDrew() {
        return isDrew;
    }

    public void setDrew(boolean isDrew) {
        this.isDrew = isDrew;
    }

    public Date getScanDate() {
        return scanDate;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate() {
        this.drawDate = new Date();
    }

    /**
     * 合并两个Present对象List，旧数据drew覆盖新数据，并去除重复
     * 
     * @param presents_new
     * @param presents_old
     * @return
     */
    public static List<Present> unionPresent(List<Present> presents_new, List<Present> presents_old) {
        List<String> link_new = new ArrayList<String>();
        List<String> link_cover = new ArrayList<String>();
        List<Present> result = new ArrayList<Present>();
        int oldCount = 0; //旧奖品沿用计数
        int coverCount = 0; //旧奖品覆盖相同新奖品计数
        int newCount = 0;//新奖品计数
        System.out.print("开始整合奖品数据...");
        for (Present p_new : presents_new) {
            link_new.add(p_new.getLink()); //将所有新奖品的链接添加到一个List中方便对应查找
        }
        for (Present p_old : presents_old) {
            // 将所有旧奖品信息添加到当前队列
            String link = p_old.getLink();
            result.add(p_old);
            if (link_new.contains(link)) { 
                //旧奖品链接在新奖品中也存在，则添加覆盖标记
                link_cover.add(link);
                coverCount++;
            }else{
                //旧奖品链接无直接存在的新奖品，直接沿用
                oldCount++;
            }
        }
        for (Present p_new : presents_new) {
            String link = p_new.getLink();
            if (link_cover.contains(link)) {
                //链接已标记，跳过当前奖品
                continue;
            } else {
                //全新奖品，添加到当前队列
                result.add(p_new);
                newCount++;
            }
        }
        System.out.println("奖品数据整合成功！");
        System.out.println("读取到的旧奖品共：" + presents_old.size() + " 个");
        System.out.println("读取到的新奖品共：" + presents_new.size() + " 个");
        System.out.println("整合之后的奖品共：" + result.size() + " 个");
        System.out.println("其中包含: 已失效旧奖品: "+oldCount+"个, 仍有效的旧奖品沿用: " + coverCount + "个，新增全新奖品: " + newCount + "个");
        return result;
    }

    /**
     * 将WebElement内的链接，文本，当前时间赋予Present对象并组成List
     * 
     * @param list 待转换的WebElement对象List
     * @param des  目标Present对象List
     */
    public static void addElement(List<WebElement> list, List<Present> des) {
        for (WebElement e : list) {
            String text = "";
            try {
                text = e.getText().toString();
            } catch (Exception e0) {
                try {
                    text = e.findElement(By.xpath("//span[@class='psnt-ttl']")).getText().toString();
                } catch (Exception e1) {
                }
            }
            if (!text.replaceAll(" ", "").equals("")) {
                des.add(new Present(e.getAttribute("href").toString(), text, false, new Date()));
            }
        }
    }
}
