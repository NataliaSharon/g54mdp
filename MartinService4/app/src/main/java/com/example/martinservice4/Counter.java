package com.example.martinservice4;

import android.util.Log;

public class Counter extends Thread implements Runnable
{
    public boolean direction = true;
    public int count = 0;
    public boolean running = true;

    public Counter()
    {
        this.start();
    }

    public void run()
    {
        while(this.running)
        {
            try {Thread.sleep(2000);} catch(Exception e) {return;}

            if(direction)
                count++;
            else
                count--;

            Log.d("g54mdp", "counter " + count);
        }

        Log.d("g54mdp", "counter thread exiting");
    }
}
