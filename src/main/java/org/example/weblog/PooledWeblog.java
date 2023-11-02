package org.example.weblog;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWeblog
{
    private static final int NUMBER_OF_THREADS = 4;

    public static void main(String[] args)
    {
        System.out.println("Start date and time: " + new Date());
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        Queue<LogEntry> results = new LinkedList<>();

        String filepath = "/tmp/access.2023_10_30.log";

        try (InputStream in = Files.newInputStream(new File(filepath).toPath())) {
            try (Reader reader = new InputStreamReader(in)) {
                try (BufferedReader br = new BufferedReader(reader)) {
                    while (br.readLine() != null){
                        String line = br.readLine();
                        LookupTask task = new LookupTask(line);
                        Future<String> future = executorService.submit(task);
                        results.add(new LogEntry(line, future));
                    }
                }
            }
        }catch (IOException e){
            System.err.println("Error occurred: " + e);
        }

        for (LogEntry result : results){
            try {
                System.out.println(result.future.get());
            }catch (InterruptedException | ExecutionException ex){
                System.out.println(result.entry);
            }
        }
        executorService.shutdown();
        System.out.println("End date and time: " + new Date());
    }

    private static class LogEntry
    {
        private String entry;
        private Future<String> future;

        public LogEntry(String entry, Future<String> future)
        {
            this.entry = entry;
            this.future = future;
        }
    }
}
