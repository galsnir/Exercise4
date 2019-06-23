package com.game.exercise4;



import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Client extends AsyncTask<String, String, Void> {
    private Socket socket;
    private DataOutputStream info;
    private BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    /**
     * creates socket and data output stream and send data from queue to server
     * @param strings
     * @return null
     */
    @Override
    protected Void doInBackground(String... strings)
    {
        try
        {
            this.socket = new Socket(strings[0], Integer.parseInt(strings[1]));
            this.info = new DataOutputStream(this.socket.getOutputStream());
            while (true)
            {
                this.info.write((queue.take()+"\r\n").getBytes());
                this.info.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * adding new info to queue
     * @param str
     */
    public void Send(String str) {
        try {
            this.queue.put(str);
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * closing client socket
     */
    public void Stop()
    {
        try
        {
            this.socket.close();
        }

        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }


}