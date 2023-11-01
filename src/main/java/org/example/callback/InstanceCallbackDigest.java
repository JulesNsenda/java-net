package org.example.callback;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest
        implements Runnable
{
    private final String filename;
    private final InstanceCallbackDigestUserInterface callback;

    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback)
    {
        this.filename = filename;
        this.callback = callback;
    }

    @Override
    public void run()
    {
        try {
            FileInputStream fis = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(fis, sha);

            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();
            callback.receiveDigest(digest);
        }catch (NoSuchAlgorithmException | IOException e){
            System.err.println(e);
        }
    }
}
