package com.example.xzy.farm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by mx on 2016/9/11.
 */
public class DetailsActivity extends Activity implements ScrollViewListener, View.OnClickListener {
    private ObservableScrollView mScrollView;
    private Button chickenbutton,duckbutton,gossiebutton;
    private TextView content;
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Code.this.finish();
                Intent intent = new Intent(DetailsActivity.this, SomeActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_draw);
        mScrollView=(ObservableScrollView)findViewById(R.id.scroll);
        mScrollView.setScrollViewListener(this);
        content = (TextView)findViewById(R.id.textView);
        chickenbutton = (Button)findViewById( R.id.chickenbutton);
        duckbutton = (Button)findViewById(R.id.duckbutton);
        gossiebutton = (Button)findViewById(R.id.gossiebutton);

        //content.setMovementMethod(ScrollingMovementMethod.getInstance());
        chickenbutton.setOnClickListener(this);
        duckbutton.setOnClickListener(this);
        gossiebutton.setOnClickListener(this);
        try{
            InputStream in = getAssets().open("chickentext.txt");
            //  获得内容的长度
            int size = in.available();

            byte[] buffer = new byte[size];
            //  把内存从inputstream内读取到数组上
            in.read(buffer);
            in.close();

            //  把内容复制给String
            String contents = new String(buffer,"GB2312");
            content.setText(contents);

            //  初始化设计滑动距离只能这样写
            //  mScrollView.smoothScrollTo()或mScrollView.mScrollView.scrollTo()均无效!
            //  根据记录的y来回到上次离开的地方
            mScrollView.post(new Runnable() {

                @Override
                public void run() {
                    //  500为模拟值,实际上可以从轻量级数据库orSQLITE获取上次记录的值
                    mScrollView.smoothScrollTo(0, 500);
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chickenbutton:
                chickenbutton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                duckbutton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                gossiebutton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                try{
                    InputStream in = getAssets().open("chickentext.txt");
                    //  获得内容的长度
                    int size = in.available();

                    byte[] buffer = new byte[size];
                    //  把内存从inputstream内读取到数组上
                    in.read(buffer);
                    in.close();

                    //  把内容复制给String
                    String contents = new String(buffer,"GB2312");
                    content.setText(contents);

                    //  初始化设计滑动距离只能这样写
                    //  mScrollView.smoothScrollTo()或mScrollView.mScrollView.scrollTo()均无效!
                    //  根据记录的y来回到上次离开的地方
                    mScrollView.post(new Runnable() {

                        @Override
                        public void run() {
                            //  500为模拟值,实际上可以从轻量级数据库orSQLITE获取上次记录的值
                            mScrollView.smoothScrollTo(0, 500);
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.duckbutton:
                chickenbutton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                duckbutton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                gossiebutton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                try{
                    InputStream in = getAssets().open("ducktext.txt");
                    //  获得内容的长度
                    int size = in.available();

                    byte[] buffer = new byte[size];
                    //  把内存从inputstream内读取到数组上
                    in.read(buffer);
                    in.close();

                    //  把内容复制给String
                    String contents = new String(buffer,"GB2312");
                    content.setText(contents);

                    //  初始化设计滑动距离只能这样写
                    //  mScrollView.smoothScrollTo()或mScrollView.mScrollView.scrollTo()均无效!
                    //  根据记录的y来回到上次离开的地方
                    mScrollView.post(new Runnable() {

                        @Override
                        public void run() {
                            //  500为模拟值,实际上可以从轻量级数据库orSQLITE获取上次记录的值
                            mScrollView.smoothScrollTo(0, 500);
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.gossiebutton:
                chickenbutton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                duckbutton.setBackgroundResource(R.drawable.circle_corner_button_unselected);
                gossiebutton.setBackgroundResource(R.drawable.circle_corner_button_selected);
                try{
                    InputStream in = getAssets().open("gossietext.txt");
                    //  获得内容的长度
                    int size = in.available();

                    byte[] buffer = new byte[size];
                    //  把内存从inputstream内读取到数组上
                    in.read(buffer);
                    in.close();

                    //  把内容复制给String
                    String contents = new String(buffer,"GB2312");
                    content.setText(contents);

                    //  初始化设计滑动距离只能这样写
                    //  mScrollView.smoothScrollTo()或mScrollView.mScrollView.scrollTo()均无效!
                    //  根据记录的y来回到上次离开的地方
                    mScrollView.post(new Runnable() {

                        @Override
                        public void run() {
                            //  500为模拟值,实际上可以从轻量级数据库orSQLITE获取上次记录的值
                            mScrollView.smoothScrollTo(0, 500);
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        //  每次滑动记录y
        //  使用SharedPreferences或者SQLITE
    }
}
