package com.example.xzy.farm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzy on 2016/9/9.
 */
public class IndividualSecurityActivity extends Activity {
    String farmID = null;
    ConnectDatabase connect;
    ExpandableListView schedule_list;
    List<String> parent = null;
    List<SecurityInfo> child_1 = null;
    List<SecurityInfo> child_2 = null;
    Map<String, List<SecurityInfo>> map = null;

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
        setContentView(R.layout.individualsecurity);
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

        map = new HashMap<String, List<SecurityInfo>>();
        // TODO 连接sqlite
        child_1 = new ArrayList<SecurityInfo>();
        child_1.add(new SecurityInfo("无", "暂无未读消息。"));
        map.put("未读", child_1);

        child_2 = new ArrayList<SecurityInfo>();
        child_2.add(new SecurityInfo("2016-9-8", "个体38号体温偏高。"));
        map.put("已读", child_2);

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
                LayoutInflater inflater = (LayoutInflater) IndividualSecurityActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.parent_list, null);
            }
            TextView text = (TextView) convertView.findViewById(R.id.parent_text);
            ImageView arrow = (ImageView) convertView.findViewById(R.id.arrow);
            text.setText(IndividualSecurityActivity.this.parent.get(groupPosition));
            if(isExpanded) arrow.setBackgroundResource(R.drawable.up_arrow);
            else arrow.setBackgroundResource(R.drawable.down_arrow);
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
            String key = IndividualSecurityActivity.this.parent.get(groupPosition);
            SecurityInfo info = map.get(key).get(childPosition);
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) IndividualSecurityActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
