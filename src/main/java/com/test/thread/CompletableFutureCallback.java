package com.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureCallback {
	public static void main(String[] args) {

		CompletableFuture<String>  data = CompletableFuture.supplyAsync(() -> {
			try {
				// simulate long running task
				Thread.sleep(2000);
			} catch (InterruptedException e) { }
			return 20;
		}).thenApply((Integer count) -> count * 10).thenApply(count -> "Finally creates a string: " + count);//register callbacks

		try {
			System.out.println(data.get());
		} catch (InterruptedException | ExecutionException e) {

		}
	}


}