package ua.com.itproekt.gup.api.rest.Queue2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/*
 * http://cybern.ru/urok-23-bibliotechnye-komponenty-priorityblockingqueue.html
 */

public class HungryStudent {

    public static BlockingQueue<Food> queue;

    public static void main(String[] args) {
        int             n = 4;
        queue             = new PriorityBlockingQueue<Food>();
        Executor executor = Executors.newCachedThreadPool();

        for(int i=0; i<n; ++i){
            executor.execute(new Kock());
        }

        while(true){
            if(!queue.isEmpty())
                System.out.println("Сожрата еда сорта" + queue.poll().vkus);
        }
    }
}
