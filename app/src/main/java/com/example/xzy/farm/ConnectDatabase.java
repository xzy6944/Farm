package com.example.xzy.farm;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.RowSet;

/**
 * Created by xzy on 2016/8/19.
 */
public class ConnectDatabase {

    public ConnectDatabase() {
    }

    public void update(final String sql){
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(0 + "\n");
                    toServer.flush();

                    toServer.write(sql + "\n");
                    toServer.flush();

                }catch (Exception ex){
                }
            }
        }.start();
    }

    public ArrayList<Category> queryCategory(final String sql){
        final ArrayList<Category>[] rs = new ArrayList[]{new ArrayList<>()};
        final boolean[] running = {true};
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(1 + "\n");
                    toServer.flush();

                    toServer.write(sql + "\n");
                    toServer.flush();

                    rs[0] = (ArrayList<Category>) oip.readObject();
                    running[0] = false;

                }catch (Exception ex){
                }
            }
        }.start();
        while (running[0]){}
        return rs[0];
    }

    public ArrayList<Daily> queryDaily(final String sql){
        final ArrayList<Daily>[] rs = new ArrayList[]{new ArrayList<>()};
        final boolean[] running = {true};
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(2 + "\n");
                    toServer.flush();

                    toServer.write(sql + "\n");
                    toServer.flush();

                    rs[0] = (ArrayList<Daily>) oip.readObject();
                    running[0] = false;

                }catch (Exception ex){
                }
            }
        }.start();
        while (running[0]){}
        return rs[0];
    }

    public ArrayList<Farm> queryFarm(final String sql){
        final ArrayList<Farm>[] rs = new ArrayList[]{new ArrayList<>()};
        final boolean[] running = {true};
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(3 + "\n");
                    toServer.flush();

                    toServer.write(sql + "\n");
                    toServer.flush();

                    rs[0] = (ArrayList<Farm>) oip.readObject();
                    running[0] = false;

                }catch (Exception ex){
                }
            }
        }.start();
        while(running[0]){}
        return rs[0];
    }

    public ArrayList<Help> queryHelp(final String sql){
        final ArrayList<Help>[] rs = new ArrayList[]{new ArrayList<>()};
        final boolean[] running = {true};
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(4 + "\n");
                    toServer.flush();

                    toServer.write(sql + "\n");
                    toServer.flush();

                    rs[0] = (ArrayList<Help>) oip.readObject();
                    running[0] = false;

                }catch (Exception ex){
                }
            }
        }.start();
        while (running[0]){}
        return rs[0];
    }

    public ArrayList<Individual> queryIndividual(final String sql){
        final ArrayList<Individual>[] rs = new ArrayList[]{new ArrayList<>()};
        final boolean[] running = {true};
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(5 + "\n");
                    toServer.flush();

                    toServer.write(sql + "\n");
                    toServer.flush();

                    rs[0] = (ArrayList<Individual>) oip.readObject();
                    running[0] = false;

                }catch (Exception ex){
                }
            }
        }.start();
        while (running[0]){}
        return rs[0];
    }

    public ArrayList<User> queryUser(final String sql){
        final ArrayList<User>[] rs = new ArrayList[]{new ArrayList<>()};
        final boolean[] running = {true};
        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("192.168.124.1", 25160);
                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gb2312"));
                    PrintWriter toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gb2312"));
                    ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
                    Log.d("MainActivity", "连接成功");

                    toServer.write(6 + "\n");
                    toServer.flush();
                    Log.d("MainActivity", "数字传递");

                    toServer.write(sql + "\n");
                    toServer.flush();
                    Log.d("MainActivity", "sql传递");

                    try {
                        rs[0] = (ArrayList<User>) oip.readObject();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Log.d("MainActivity", "返回成功");
                    running[0] = false;

                }catch (Exception ex){
                }
            }
        }.start();
        while(running[0]){}
        return rs[0];
    }

}
