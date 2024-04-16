package com.example.tugaskuliahasinkron;

import android.os.Handler;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.Message;


import com.google.gson.Gson;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class TodoThread extends Thread {
    private final Handler h;

    public TodoThread(Handler h) {
        this.h = h;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL("https://mgm.ub.ac.id/todo.php");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);

            Gson gson = new Gson();
            if(conn.getResponseCode()==200){
                //baca respon stream dari server
                //convert ke string
                InputStream is =conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader br =new BufferedReader(
                        new InputStreamReader(is)
                );
                String line="";
                while((line=br.readLine())!=null) {
                    sb.append(line);
                }
                br.close();
                String todoJson = sb.toString();
                //DataTodo dataTodo = gson.fromJson(todoJson,DataTodo.class);

                DataTodo[] dataTodos = gson.fromJson(todoJson, DataTodo[].class);

                Message message = h.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putSerializable("todos", dataTodos);
//                bundle.putString("id",dataTodo.id);
//                bundle.putString("what",dataTodo.what);
//                bundle.putString("time",dataTodo.time);
                message.setData(bundle);
                h.sendMessage(message);
            }else{}
        }catch (Exception e) {}
    }
}
