package demo.ksq.com.handlerthreaddemo;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //创建主线程的handler
    private Handler handler;

    //创建子线程的handler
    private HandlerThread handlerThread;
    private Handler handler_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Message message = new Message();
                Log.d("mainThread------>", "mainThread------>");
                handler_2.sendMessageDelayed(message, 1000);
            }
        };


        handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        handler_2 = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Message message = new Message();
                Log.d("handlerThread------>", "handlerThread------>");
                handler.sendMessageDelayed(message, 1000);
            }
        };

    }

    public void start(View view) {
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        handler.sendEmptyMessage(1);
    }

    public void stop(View view) {
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        handler.removeMessages(1);
    }
}
