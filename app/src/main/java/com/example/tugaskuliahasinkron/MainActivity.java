package com.example.tugaskuliahasinkron;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvTodo=findViewById(R.id.rvTodo);

        Handler h = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle =msg.getData();
                DataTodo[] dataTodos = (DataTodo[]) bundle.getSerializable("todos");
                List<DataTodo> dataList = Arrays.asList(dataTodos);
                TodoAdapter todoAdapter = new TodoAdapter(MainActivity.this, dataList);
                rvTodo.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvTodo.setAdapter(todoAdapter);
            }
        };
        Thread t= new TodoThread(h);
        t.start();

//        TodoAdapter todoAdapter =
//                new TodoAdapter(MainActivity.this, dataTodos);
//
//        RecyclerView.LayoutManager lm =
//                new LinearLayoutManager(MainActivity.this);
//        this.rvTodo.setLayoutManager(lm);
//        this.rvTodo.setAdapter(todoAdapter);

    }
}