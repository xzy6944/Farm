package com.example.xzy.farm;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xzy on 2016/9/14.
 */
public class ReceiveService extends Service{

    int d = 0;
    String s;
    int co2 = 1;
    int gz = 2;
    int wd = 3;
    int sd = 4;
    private Timer timer;
    private TimerTask task;

    private ScheduleHelper mScheduleHelper;
    private SQLiteDatabase db;
    Date date;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DataBinder mBinder = new DataBinder();

    class DataBinder extends Binder{
        public int returnCO2(){
            return  co2;
        }

        public int returnGZ(){
            return  gz;
        }

        public int returnWD(){
            return  wd;
        }

        public int returnSD(){
            return  sd;
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1://co2
                case 2://温度高
                case 3://温度低
                {
                    Log.d("ReceiveService", "alert");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReceiveService.this);
                    builder.setTitle("安全提示");
                    builder.setMessage(s.replace("\n", ""))
                            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog ad = builder.create();
                    ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    ad.setCanceledOnTouchOutside(false);
                    ad.show();
                }
                    break;
                case 4:
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReceiveService.this);
                    builder.setTitle("安全提示");
                    builder.setMessage(s.replace("\n", ""))
                            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog ad = builder.create();
                    ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    ad.setCanceledOnTouchOutside(false);
                    ad.show();

                    date = new Date();
                    db.execSQL("insert into Schedule (type, time, details, read) values(?, ?, ?, ?)", new String[]{"0", format.format(date), s.replace("\n", ""), "0"});
                }
                    break;
                case 5:
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReceiveService.this);
                    builder.setTitle("安全提示");
                    builder.setMessage(s.replace("\n", ""))
                            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog ad = builder.create();
                    ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    ad.setCanceledOnTouchOutside(false);
                    ad.show();

                    date = new Date();
                    db.execSQL("insert into Schedule (type, time, details, read) values(?, ?, ?, ?)", new String[]{"1", format.format(date), s.replace("\n", ""), "0"});
                }
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mScheduleHelper = new ScheduleHelper(this, "Schedule.db", null, 1);
        db = mScheduleHelper.getWritableDatabase();
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("123.206.14.34", 25162);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    Log.d("ReceiveService", "连接成功");
                    Message message = new Message();
                    while(true){
                        s = fromServer.readLine();
                        wd = Integer.parseInt(s.replace("\n", ""));
                        s = fromServer.readLine();
                        sd = Integer.parseInt(s.replace("\n", ""));
                        s = fromServer.readLine();
                        co2 = Integer.parseInt(s.replace("\n", ""));
                        s = fromServer.readLine();
                        gz = Integer.parseInt(s.replace("\n", ""));

                        Log.d("ReceiveService", "read");
                    }

                }catch (Exception ex){
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("123.206.14.34", 25164);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    Log.d("ReceiveService", "连接成功");
                    Message message = new Message();
                    while(true){
                        s = fromServer.readLine();
                        d = Integer.parseInt(s.replace("\n", ""));
                        s = fromServer.readLine();
                        Log.d("ReceiveService", "read");

                        message.what = d;
                        mHandler.sendMessage(message);
                    }

                }catch (Exception ex){
                }
            }
        }.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(20000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Message message = new Message();
//                s = "养殖场温度过高！";
//                message.what = 2;
//                mHandler.sendMessage(message);
//            }
//        }).start();

        final Intent intentWD = new Intent();
        intentWD.setAction("WD");
        final Intent intentSD = new Intent();
        intentSD.setAction("SD");
        final Intent intentCO2 = new Intent();
        intentCO2.setAction("CO2");
        final Intent intentGZ = new Intent();
        intentGZ.setAction("GZ");

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                intentWD.putExtra("WD", wd + "");
                sendBroadcast(intentWD);
                intentSD.putExtra("SD", sd + "");
                sendBroadcast(intentSD);
                intentGZ.putExtra("GZ", gz + "");
                sendBroadcast(intentGZ);
                intentCO2.putExtra("CO2", co2 + "");
                sendBroadcast(intentCO2);
            }
        };
        timer.schedule(task, 0, 4000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
