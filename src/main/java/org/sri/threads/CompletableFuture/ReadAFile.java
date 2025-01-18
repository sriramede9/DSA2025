package org.sri.threads.CompletableFuture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReadAFile {
    public static void main(String[] args) {
//        readAFile();
        readMultipleFiles();
    }


    private static void readMultipleFiles() {
        String[] multipleFiles = new String[]{"/home/sri/DSA/DSA/src/test/sample.txt", "/home/sri/DSA/DSA/src/test/sample2.txt"};
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try {
            Arrays.stream(multipleFiles).map(f -> {
                return CompletableFuture.supplyAsync(() -> {

                            try {
                                return new String(Files.readAllBytes(Paths.get(f)));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }).thenApplyAsync(result -> result.replace('s', 'W'))
                        .exceptionally(ex -> {
                            System.out.println("exception reading a file");
                            return "default response";
                        }).join();
            }).forEach(System.out::println);

        } finally {
            executorService.shutdown();
        }
    }


    private static void readAFile() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {

            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                        //read a file
                        try {
                            return new String(Files.readAllBytes(Paths.get("/home/sri/DSA/DSA/src/test/sample3.txt")));
                        } catch (IOException e) {
                            throw new RuntimeException("Error reading file", e);
                        }
                    }, executorService)
                    .thenApplyAsync(result -> result.replace('s', 'w'))
                    .exceptionally(ex -> {
                        System.out.println("exception with file, maybe missing");
                        return "default string to process";
                    });
            System.out.println(completableFuture.join());
        } finally {
            executorService.shutdown();
        }
    }
}
