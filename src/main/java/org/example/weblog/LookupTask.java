package org.example.weblog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class LookupTask
        implements Callable<String>
{
    private final String line;

    public LookupTask(String line)
    {
        this.line = line;
    }

    @Override
    public String call()
            throws Exception
    {
        String ip = line.split("\\s")[2];
        try {
            InetAddress address = InetAddress.getByName(ip);
            return ip + " " + address.getHostName();
        }catch (UnknownHostException ex){
            return line;
        }
    }
}
