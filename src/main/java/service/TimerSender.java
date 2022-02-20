package service;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSender {
    public static void sendByTimer() {
        System.out.println("method send by timer - start");
        Calendar startCalendar = Calendar.getInstance();
//        long day = 86_400_000L;
        long day = 10000;
        //проверка времени запуска

        if (Calendar.getInstance().getTime().getHours() < 15) {
//            startCalendar.set(Calendar.HOUR_OF_DAY, 15);
//            startCalendar.set(Calendar.MINUTE, 0);
//            startCalendar.set(Calendar.SECOND, 0);

            startCalendar.set(Calendar.DATE, Calendar.getInstance().getTime().getDate());
            startCalendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getTime().getHours());
            startCalendar.set(Calendar.MINUTE, Calendar.getInstance().getTime().getMinutes());
            startCalendar.set(Calendar.SECOND, Calendar.getInstance().getTime().getSeconds());
        } else {
            startCalendar.set(Calendar.DATE, Calendar.getInstance().getTime().getDate()+1);
            startCalendar.set(Calendar.HOUR_OF_DAY, 15);
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);


        }

//        назначаю таску
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer tasker");
                PDFConverter.convertToPdf();
                EmailSender.emailSender();
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, startCalendar.getTime(), day);
        System.out.println("method sens by timer - end");

        try {
            Thread.currentThread().join();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }

}
