package org.example.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetProtocol
{
    public static void main(String[] args)
    {
        try {
            InetAddress a1 = InetAddress.getByName("www.oreilly.com");

            System.out.println(a1);

            InetAddress me = InetAddress.getLocalHost();

            System.out.println(me);

        }catch (UnknownHostException e){
            throw new RuntimeException(e);
        }
    }
}
