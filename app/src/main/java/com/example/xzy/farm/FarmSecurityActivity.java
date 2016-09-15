package com.example.xzy.farm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by xzy on 2016/9/9.
 */
public class FarmSecurityActivity extends Activity{
    String farmID = null;
    ConnectDatabase connect;
    ExpandableListView schedule_list;
    List<String> parent = null;
    List<SecurityInfo> child_1 = null;
    List<SecurityInfo> child_2 = null;
    Map<String, List<SecurityInfo>> map = null;

    private ScheduleHelper mScheduleHelper;
    private SQLiteDatabase db;
    Date date;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    class SecurityInfo{
        String time = null;
        String details = null;

        public SecurityInfo(String time, String details) {
            this.time = time;
            this.details = details;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmsecurity);
        Intent intent = getIntent();
        farmID = intent.getStringExtra("farmID");

        schedule_list = (ExpandableListView) findViewById(R.id.security_list);

        initData();
        schedule_list.setAdapter(new MyAdapter());

    }

    public void initData(){
        parent = new ArrayList<String>();
        parent.add("未读");
        parent.add("已读");

        mScheduleHelper = new ScheduleHelper(this, "Schedule.db", null, 1);
        db = mScheduleHelper.getWritableDatabase();

        map = new HashMap<String, List<SecurityInfo>>();

        // TODO 连接sqlite
        child_1 = new ArrayList<SecurityInfo>();
        Cursor c = db.rawQuery("select * from Schedule where type = 0 and read = 0", null);
        if (c.getCount() == 0){
            child_1.add(new SecurityInfo("无", "暂无未读消息。"));
        }else{
            if(c.moveToFirst()){
                do{
                    child_1.add(new SecurityInfo(c.getString(c.getColumnIndex("time")), c.getString(c.getColumnIndex("details"))));
                }while (c.moveToNext());
            }
            c.close();
        }
        map.put("未读", child_1);

        child_2 = new ArrayList<SecurityInfo>();
        c = db.rawQuery("select * from Schedule where type = 0 and read = 1", null);//TODO
        if (c.getCount() == 0){
            child_2.add(new SecurityInfo("无", "暂无已读消息。"));
        }else{
            if(c.moveToFirst()){
                do{
                    child_2.add(new SecurityInfo(c.getString(c.getColumnIndex("time")), c.getString(c.getColumnIndex("details"))));
                }while (c.moveToNext());
            }
            c.close();
        }
        map.put("已读", child_2);

        db.execSQL("update Schedule set read = 1 where type = 0 and read = 0");
    }

    class MyAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            return map.get(key).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return map.get(key).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) FarmSecurityActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.parent_list, null);
            }
            TextView text = (TextView) convertView.findViewById(R.id.parent_text);
            ImageView arrow = (ImageView) convertView.findViewById(R.id.arrow);
            text.setText(FarmSecurityActivity.this.parent.get(groupPosition));
            if(isExpanded) arrow.setBackgroundResource(R.drawable.up_arrow);
            else arrow.setBackgroundResource(R.drawable.down_arrow);
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
            String key = FarmSecurityActivity.this.parent.get(groupPosition);
            SecurityInfo info = map.get(key).get(childPosition);
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) FarmSecurityActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.security_list, null);
            }
            TextView time = (TextView) convertView.findViewById(R.id.child_time);
            time.setText(info.time);
            TextView text = (TextView) convertView.findViewById(R.id.child_text);
            text.setText(info.details);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
