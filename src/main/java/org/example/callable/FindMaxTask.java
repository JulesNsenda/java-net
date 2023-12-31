package org.example.callable;

import java.util.concurrent.Callable;

public class FindMaxTask
        implements Callable<Integer>
{
    private final int[] data;
    private final int start;
    private final int end;

    public FindMaxTask(int[] data, int start, int end)
    {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call()
            throws Exception
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++){
            if (data[i] > max){
                max = data[i];
            }
        }
        return max;
    }
}

