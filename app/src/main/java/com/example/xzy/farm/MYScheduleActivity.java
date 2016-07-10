package com.example.xzy.farm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by xzy on 2016/7/9.
 */
public class MYScheduleActivity extends Activity {

    ExpandableListView schedule_list;
    List<String> parent = null;
    List<String> child_1 = null;
    List<String> child_2 = null;
    Map<String, List<String>> map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myschedule);
        schedule_list = (ExpandableListView) findViewById(R.id.schedule_list);
        TextView date = (TextView) findViewById(R.id.schedule_date);
        Calendar cal = Calendar.getInstance();
        date.setText(cal.get(Calendar.MONTH) + " 月 " + cal.get(Calendar.DAY_OF_MONTH) + " 日");

        initData();
        schedule_list.setAdapter(new MyAdapter());
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void initData(){
        parent = new ArrayList<String>();
        parent.add("今日");
        parent.add("本周");

        map = new HashMap<String, List<String>>();

        child_1 = new ArrayList<String>();
        child_1.add("注射疫苗");
        child_1.add("购进新型疫苗");
        map.put("今日", child_1);

        child_2 = new ArrayList<String>();
        child_2.add("暂无");
        map.put("本周", child_2);

    }

    class MyAdapter extends BaseExpandableListAdapter{
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
                LayoutInflater inflater = (LayoutInflater) MYScheduleActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.parent_list, null);
            }
            TextView text = (TextView) convertView.findViewById(R.id.parent_text);
            ImageView arrow = (ImageView) convertView.findViewById(R.id.arrow);
            text.setText(MYScheduleActivity.this.parent.get(groupPosition));
            if(isExpanded) arrow.setBackgroundResource(R.drawable.up_arrow);
            else arrow.setBackgroundResource(R.drawable.down_arrow);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String key = MYScheduleActivity.this.parent.get(groupPosition);
            String info = map.get(key).get(childPosition);
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) MYScheduleActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_list, null);
            }
            TextView text = (TextView) convertView.findViewById(R.id.child_text);
            text.setText(info);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
