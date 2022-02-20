package service;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSender {
    public static void sendByTimer() {


        Calendar startCalendar = Calendar.getInstance();
        long day = 86_400_000L;
        //проверка времени запуска

        if (Calendar.getInstance().getTime().getHours() < 15) {
            startCalendar.set(Calendar.HOUR_OF_DAY, 15);
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);
        } else {
            startCalendar.set(Calendar.DATE, Calendar.getInstance().getTime().getDate() + 1);
            startCalendar.set(Calendar.HOUR_OF_DAY, 15);
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);
        }

//        назначаю таску
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                PDFConverter.convertToPdf();
                EmailSender.emailSender();
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, startCalendar.getTime(), day);

        try {
            Thread.currentThread().join();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }
}
