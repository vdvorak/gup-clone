package ua.com.gup.search.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ua.com.gup.search.repository.ESCategoryRepository;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configuration
public class CLApp implements CommandLineRunner {
    @Autowired
    private ESCategoryRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Void> future = executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
                for (int i = 0; i < 100_000; i++) {
                    repository.createOffer();
                }
                return null;
            }
        });
    }
}
