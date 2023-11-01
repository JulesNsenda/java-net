package org.example.spammer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck
{
    private static final String BLACK_HOLE = "sbl.spamhaus.org";

    public static void main(String[] args)
    {
        String ip = "164.90.234.134";

        if (isSpammer(ip)){
            System.out.println(ip + " appears illegitimate");
        }else{
            System.out.println(ip + " appears legitimate");
        }

    }

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();

            StringBuilder query = new StringBuilder(BLACK_HOLE);
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query.insert(0, unsignedByte + ".");
            }

            InetAddress ignore = InetAddress.getByName(query.toString());
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

}
