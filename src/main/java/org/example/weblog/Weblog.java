package org.example.weblog;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;

public class Weblog
{
    public static void main(String[] args)
    {
        String filepath = "/tmp/access.2023_10_30.log";

        try (InputStream in = Files.newInputStream(new File(filepath).toPath())) {
            try (Reader reader = new InputStreamReader(in)) {
                try (BufferedReader br = new BufferedReader(reader)) {
                    while (br.readLine() != null){
                        String line = br.readLine();
                        if (line == null){
                            System.exit(2);
                        }
                        String ip = line.split("\\s")[2];
                        try {
                            InetAddress address = InetAddress.getByName(ip);
                            System.out.println("Hostname for ip: " + ip + " is -> " + address.getHostName());
                        }catch (UnknownHostException ex){
                            System.err.println(line);
                        }
                    }
                }
            }

        }catch (IOException e){
            System.err.println("Error occurred: " + e);
        }
    }
}
