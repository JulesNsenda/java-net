package org.example.urlanduri;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SampleURL
{
    public static void main(String[] args)
            throws IOException
    {
        URL url = new URL("https://www.techamt.com");
        try (InputStream in = url.openStream()) {
            int c;
            while ((c = in.read()) != -1){
                System.out.write(c);
            }
        }
    }
}
