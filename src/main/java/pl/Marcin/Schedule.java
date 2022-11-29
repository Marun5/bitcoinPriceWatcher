package pl.Marcin;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Schedule {
    private final int PERIOD = 1000*15;
    private final String BITCOIN_PRICE = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private final Price price = new Price();
    public Timer timer;
    public void launchTimer() {
        timer = new Timer();

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                try {
                    price.getPrice(BITCOIN_PRICE);
                    priceCheck();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(tt,0,PERIOD);
    }

    private void priceCheck() {
        if(price.isHigher()){
            displayNotification(price.msgHigher());
        } else if(price.isLower()){
            displayNotification(price.msgLower());
        }
    }

    private void displayNotification(String message) {
        if(!SystemTray.isSupported()) {
            System.out.println("System Tray is not supported");
            return;
        }
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("bitcoin.png");

        TrayIcon trayIcon = new TrayIcon(image, "Bitcoin Watcher Notification");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Bitcoin Watcher");

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        trayIcon.displayMessage("Bitcoin Watcher", message, TrayIcon.MessageType.INFO);
    }
}
