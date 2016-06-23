package ua.com.itproekt.gup.api.rest.Queue2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Kock implements Runnable {

    static public int count = 0;
    private int          id = count++;

    public void run() {
        while(true){
            Random   r = new Random();
            TimeUnit t = TimeUnit.MILLISECONDS;

            try {
                t.sleep(r.nextInt() % 10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            HungryStudent.queue.add(new Food(id));
        }
    }

    private void sleep(int i) {
    }
}
