package listener;

import service.TimerSender;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("start");
        TimerSender.sendByTimer();

        try {
            Thread.currentThread().join();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("listener end");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}