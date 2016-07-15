package ua.com.itproekt.gup.api.rest.Queue1;

import java.util.LinkedHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        final String[]      names = {
                "A",
                "BB",
                "CCC",
                "DDDD",
                "EEEEE",
                "FFFFFF",
                "GGGGGGG",
                "HHHHHHHH",
                "IIIIIIIII",
                "JJJJJJJJJJ",
                "KKKKKKKKKKK",
                "LLLLLLLLLLLL",
                "MMMMMMMMMMMMM",
                "NNNNNNNNNNNNNN",
                "OOOOOOOOOOOOOOO",
                "PPPPPPPPPPPPPPPP",
                "QQQQQQQQQQQQQQQQQ",
                "RRRRRRRRRRRRRRRRRR",
        };

        final BlockingQueue queue = new PriorityBlockingQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<names.length; ++i) {
                    try {
                        Thread.sleep(1000);
                        queue.put(names[i]);
                        System.out.println(">> (" + i + "/" + queue.size() + ") " + names[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Producer").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<names.length; ++i) {
                        Thread.sleep(3000);
                        System.out.println("<< (" + queue.size() + ") " + queue.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}
