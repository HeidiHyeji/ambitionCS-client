package com.example.gogo6.myapp;

/**
 * Created by 김혜지 on 2016-11-26.
 *
 * 소켓스레드를 모든 액티비티와 공유하기 위함.
 *
 */


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientService extends Service
    {
        private Handler mHandler = new Handler();
        private BufferedReader networkReader = null;
        private BufferedWriter networkWriter = null;
        private ClientThread  clientThread = null;
        private static final int READMSG =1;
        String read_msg="";
        @Override
        public void onCreate()
        {
            super.onCreate();
            Log.i("clientservice", "onCreate()");

            // 노티피케이션 객체 생성
            Intent intent = new Intent(this, MainActivity.class );
            PendingIntent pIntent = PendingIntent.getActivity( this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Notification noti = new NotificationCompat.Builder(this).setContentTitle("AmbitionCS Client Service")
                    .setContentText("Running AmbitionCS Client Service")
                    .setSmallIcon( R.drawable.logoservice)
                    .setContentIntent( pIntent )
                    .build();

            // 포그라운드 서비스 설정 (지각할 수 있는 서비스가 된다.)
            startForeground( 1234, noti );
        }

        @Override
        public int onStartCommand( Intent intent,int flags, int startId )
        {
            super.onStartCommand( intent, flags, startId );
            String ip = intent.getExtras().getString("ip");

            Log.i("clientservice", "onStartCommand() : " + intent);

            if( clientThread == null)
            {
                clientThread = new ClientThread(ip,mHandler);
                clientThread.start();
                networkWriter=clientThread.getNetworkWriter();
            }
            return START_REDELIVER_INTENT;
        }

        @Override
        public void onDestroy()
        {
            Log.i("clientservice", "onDestroy()");

            stopForeground( true );

            if( clientThread != null )
            {
                clientThread.quit();
                clientThread.interrupt();
                clientThread = null;
            }

            super.onDestroy();
        }

        IClientService.Stub mBinder = new IClientService.Stub()
        {
            public void writeMsgToServer(String write_msg, IClientServiceCallback callback) throws RemoteException {
                if(networkWriter != null){
                    try{
                        networkWriter.write(write_msg);
                        networkWriter.newLine();
                        networkWriter.flush();
                    }catch (IOException e){}
                }


                mHandler = new Handler(){
                    public void handleMessage(android.os.Message msg){
                        switch (msg.what) {
                            case READMSG:
                                read_msg.concat((String)msg.obj);
                                break;
                        }
                    }
                };

                callback.readMsgFromServer(read_msg);
            }
        };

        @Override
        public IBinder onBind( Intent intent )
        {
            Log.i("clientservice", "onBind()");
            return mBinder;
        }

        @Override
        public boolean onUnbind( Intent intent )
        {
            Log.i("clientservice", "onUnbind()");
            return super.onUnbind( intent );
        }
    }

