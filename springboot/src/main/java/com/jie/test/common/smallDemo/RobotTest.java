package com.jie.test.common.smallDemo;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RobotTest {
    public static void main(String[] args) throws AWTException {
        Robot robot=new Robot();
        while (true){
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(200);
            for(int i=0;i<4;i++){
                robot.keyPress(KeyEvent.VK_2);
            }
            for (int i=0;i<15;i++){
                robot.delay(500);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_Z);
        }
    }
    //zv22
}
