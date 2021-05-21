package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
//
public class MainActivity extends AppCompatActivity {
    ArrayList<Task> tasks = new ArrayList<>();
    TextView textView1, textView2, textView3, textView4, textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.c1);
        textView2 = findViewById(R.id.c2);
        textView3 = findViewById(R.id.c3);
        textView4 = findViewById(R.id.c4);
        textView = findViewById(R.id.text_view1);
        TasksLoader tasksLoader = new TasksLoader();
        tasksLoader.setOnPostExecute(new TasksLoader.OnPostExecute() {
            @Override
            public void doOnPostExecute(TaskAnswer answer) {
                tasks = answer.data;

                setTask(tasks.get(0));
            }
        });

        tasksLoader.execute("http://10.148.190.161");
    }

    void setTask(Task task){
        textView1.setText(task.variants.get(0));
        textView2.setText(task.variants.get(1));
        textView3.setText(task.variants.get(2));
        textView4.setText(task.variants.get(3));
        textView.setText(task.text);
        if (task.type==1){
            setContentView(R.layout.activity_main);
        }
        if (task.type==2){
            setContentView(R.layout.activity_main);
        }
    }
}
