package com.example.admin.getaccel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
//今回使う変数の宣言->これはどの関数でも使うためにここで宣言する
    private  static SensorManager manager;//センサーの値を受け取るために必要な変数
    private  static TextView XTextView;
    private  static TextView YTextView;
    private  static TextView ZTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //センサーを管理する機能をAndroidOSから呼び出す
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //テキスト表示エリアを変数として保存
        XTextView = (TextView)findViewById(R.id.XTextView);
        YTextView = (TextView)findViewById(R.id.YTextView);
        ZTextView = (TextView)findViewById(R.id.ZTextView);
    }

    protected void onStop(){
        super.onStop();
        manager.unregisterListener(this);
    }

    protected  void onResume() {
        super.onResume();
        List<Sensor> sensors = manager.getSensorList((Sensor.TYPE_ACCELEROMETER));
        if (sensors.size() > 0) {   //センサーがそのデバイスにある時
            Sensor s = sensors.get(0);
            //センサーをマネージャーに管理してもらう
            manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //加側道を取得
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            XTextView.setText("x : " + event.values[0]);
            YTextView.setText("y : " + event.values[1]);
            ZTextView.setText("z : " + event.values[2]);
        }
        //テキスト表示エリアに加速度を書き込む
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
