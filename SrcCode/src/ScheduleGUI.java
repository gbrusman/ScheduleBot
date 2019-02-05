package com.schedulebot.gabrielbrusman;
import javax.swing.*;



public class ScheduleGUI {
    private JPanel Card1 = new JPanel();
    private JTextArea welcomeText;
    private JPanel welcomePanel = new JPanel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("ScheduleGUI");
        frame.setContentPane(new ScheduleGUI().Card1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
