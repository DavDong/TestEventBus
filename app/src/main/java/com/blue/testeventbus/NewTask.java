package com.blue.testeventbus;

import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/13.
 */
public class NewTask {
    private String result = "";
    private static final int SUCESS = 0x23;
    private static final int ERROR = 0x24;

    public String getResult(){
        return result;
    }

    public void runTask(){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                //模拟耗时请求操作
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                result = "use EventBus get message";
                return SUCESS;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                if (integer == SUCESS){
                    EventBus.getDefault().post(NewTask.this);//发送数据
                }
            }
        }.execute();
    }
}
