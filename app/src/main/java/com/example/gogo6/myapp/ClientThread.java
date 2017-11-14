package com.example.gogo6.myapp;

/**
 * Created by 김혜지 on 2016-11-26.
 *
 * 서버와의 소켓통신을 하는 클라이언트를
 * 스레드로 정의
 */

import android.os.*;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class ClientThread extends Thread {
    Boolean loop;
    SocketAddress socketAddress;
    Socket socket;
    String line;
    private final int connection_timeout = 600000; //타임아웃 시간(10분)
    private final int port = 8007;
    private Handler mHandler;
    private BufferedReader networkReader = null;
    private BufferedWriter networkWriter = null;
    private static final int READMSG =1;



    public ClientThread(String ip){
        socketAddress = new InetSocketAddress(ip, port); //IP주소와 포트번호를 사용하여 SocketAddress객체 생성
    }
    public ClientThread(String ip, Handler mHandler) {
        socketAddress = new InetSocketAddress(ip, port); //IP주소와 포트번호를 사용하여 SocketAddress객체 생성
        this.mHandler=mHandler;
    }
    public BufferedReader getNetworkReader() {
        return networkReader;
    }

    public BufferedWriter getNetworkWriter() {
        return networkWriter;
    }

    public void run() {
        try {
            socket = new Socket();  //소켓생성
            socket.setSoTimeout(connection_timeout); //read하는 동안 packet이 없을 시, connection이 끊김.
            socket.setSoLinger(true, connection_timeout);

            //블록 모드로 작동하는 connect() 메소드에서 반환되면 서버와 정상연결
            socket.connect(socketAddress, 3000); //서버와 연결 timeout 3초
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //서버한테 메세지를 쓰는 객체
            InputStreamReader i = new InputStreamReader(socket.getInputStream());
            networkReader = new BufferedReader(i);  //서버로부터의 메세지를 읽는 객체

            Runnable showUpdate = new Runnable() {
                @Override
                public void run() { //정상적으로 서버에 접속

                }
            };
            Log.d("연결","정상서버접속");
            mHandler.post(showUpdate);  //정상접속시 바뀔 UI 핸들러에 전송
            loop = true;
        } catch (Exception e) {
            loop = false;
            e.printStackTrace();
        }

        while (loop) {
            try {
                line = networkReader.readLine(); //서버로 부터 오는 메세지 읽기
                android.os.Message read_msg=new Message();
                read_msg.what = READMSG;
                read_msg.obj=(line);
                mHandler.sendMessage(read_msg);
                Runnable showUpdate = new Runnable() {
                    @Override
                    public void run() {  //서버로 부터 온 메세지(line)을 UI에 설정하고

                    }
                };
                Log.d("받은메세지",line);

                mHandler.post(showUpdate);//핸들러를 통해 업데이트한다.
            } catch (InterruptedIOException e) {
            } catch (IOException e) {
                if (loop) {
                    loop = false;
                    Runnable showUpdate = new Runnable() {
                        @Override
                        public void run() { //알수 없는 이유에 의하여 네트워크가 끊어졌습니다
                        }
                    };
                    mHandler.post(showUpdate);
                }
                //	e.printStackTrace();
            } catch (NullPointerException npx) {
                loop = false;
                npx.printStackTrace();
                break;
            }
        }
        //서버로 부터 온 메세지가 null이라면 loop종료 되고 객체를을 닫음
        try {
            if (networkWriter != null) {
                networkWriter.close();
                networkWriter = null;
            }
            if (networkReader != null) {
                networkReader.close();
                networkReader = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
            //client = null;
            if (loop) {
                Runnable showUpdate = new Runnable() {
                    @Override
                    public void run() { //서버에서 종료시켰습니다
                    }
                };
                mHandler.post(showUpdate);
            }
        } catch (IOException e) {
            //	e.printStackTrace();
        }
    }

    public void quit() { //소켓 닫기
        loop = false;
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
            Thread.sleep(connection_timeout);
        } catch (InterruptedException e) {
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
}
