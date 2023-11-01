package org.example.callback;

import javax.xml.bind.DatatypeConverter;

public class InstanceCallbackDigestUserInterface
{
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename)
    {
        this.filename = filename;
    }

    public void calculateDigest()
    {
        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
        Thread t = new Thread(cb);
        t.start();
    }

    public void receiveDigest(byte[] digest)
    {
        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString()
    {
        String result = filename.concat(": ");
        if (digest != null){
            result += DatatypeConverter.printHexBinary(digest);
        }else{
            result += "digest not available";
        }
        return result;
    }

    public static void main(String[] args)
    {
        String path = "C:\\Users\\nsenda\\Downloads\\";
        String[] filenames = new String[]{path.concat("day.jpg"), path.concat("night.jpg")};

        for (String filename : filenames){
            InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(filename);
            d.calculateDigest();

        }
    }
}
