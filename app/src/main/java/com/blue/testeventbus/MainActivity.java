package com.blue.testeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button bt;
    private NewTask newTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        newTask = new NewTask();

        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask.runTask();//点击执行耗时方法
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEvent(Object object){
        if (object instanceof NewTask){
            String result = ((NewTask) object).getResult();//接收数据在UI线程刷新
            tv.setText(result);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
