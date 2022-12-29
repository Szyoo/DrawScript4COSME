package com.szyoo.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;

public class SwingGUI extends JFrame {
    // JFrame frame = new JFrame("@COSME 抽奖程序");

    public SwingGUI(String title) {
        super(title);
        JPanel rootPanel = new JPanel();
        this.setContentPane(rootPanel);
        rootPanel.add(new JLabel("@COSME抽奖程序，点击开始"));
        JButton bottonRun = new JButton("开始运行");
        rootPanel.add(bottonRun);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        JTextField textField =  new JTextField("初始值", 20);
        rootPanel.add(textField);

        // 内部类
        // bottonRun.addActionListener( new SwingActionListener());
        // 匿名内部类
        // bottonRun.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // System.out.println("开始运行");
        // }
        // });
        // Lambda表达式
        bottonRun.addActionListener((e) -> {
            System.out.println(textField.getText());
        });

    }

    // 内部类
    // private class SwingActionListener implements ActionListener{
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // System.out.println("开始运行");
    // }

    // }

}
