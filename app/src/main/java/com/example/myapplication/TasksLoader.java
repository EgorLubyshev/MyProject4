package com.example.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class TasksLoader extends AsyncTask<String, Integer, TaskAnswer>{
    interface OnPostExecute{
        void doOnPostExecute(TaskAnswer answer);
    }

    private OnPostExecute onPostExecute = new OnPostExecute() {
        @Override
        public void doOnPostExecute(TaskAnswer answer) {

        }
    };

    void setOnPostExecute(OnPostExecute onPostExecute){
        this.onPostExecute = onPostExecute;
    }


    @Override
    protected TaskAnswer doInBackground(String... strings) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(strings[0])
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TaskService taskService = retrofit.create(TaskService.class);
        Call<TaskAnswer> call = taskService.getTasks();
        try {
            Response<TaskAnswer> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(TaskAnswer answer) {
        super.onPostExecute(answer);
        onPostExecute.doOnPostExecute(answer);
    }


}

class Task{
    int type, id;
    String text;
    ArrayList<String> variants;
}

class TaskAnswer{
    ArrayList<Task> data;
    boolean status;
}

interface TaskService{
    @GET("/Egor/get_tasks.php")
    Call<TaskAnswer> getTasks();
}