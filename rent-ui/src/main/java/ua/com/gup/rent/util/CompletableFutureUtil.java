package ua.com.gup.rent.util;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureUtil {

    public static <T> CompletableFuture<ResponseEntity<T>> toCompletableFuture(ListenableFuture<ResponseEntity<T>> listenableFuture) {
        final CompletableFuture<ResponseEntity<T>> completableFuture = new CompletableFuture<>();

        listenableFuture.addCallback(new ListenableFutureCallback<ResponseEntity<T>>() {
            @Override
            public void onSuccess(ResponseEntity<T> responseEntity) {
                completableFuture.complete(responseEntity);

            }

            @Override
            public void onFailure(Throwable throwable) {
                completableFuture.completeExceptionally(throwable);
            }
        });

        return completableFuture;
    }
}
