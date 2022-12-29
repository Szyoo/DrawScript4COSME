package com.szyoo;

import java.util.List;


import com.szyoo.draw.Draw;
import com.szyoo.draw.Find;
import com.szyoo.jackson.JsonConverter;

public class Main {
    public static void main(String[] args) {

        

        System.out.println("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<\n| Welcome to Take a Shot |\n>>>>>>>> (￣ω￣= >>>>>>>>>\n");

        List<Present> presents = JsonConverter.updateList(Find.searchPresentToList());

        Draw.drawAll(presents);

        System.out.println("本次抽奖流程完成\n总计抽取奖品: " + Present.showDraw() + " 次");

        JsonConverter.writeJsonFile(presents);
        Driver.driver.close();

        System.out.println("程序即将退出，祝你好运～ (￣ω￣=");
    }
}
