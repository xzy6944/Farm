package com.example.xzy.farm;

/**
 * Created by mx on 2016/9/4.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class AverageTemperatureChart extends AbstractDemoChart {
    public String getName() {
        return "市场趋势";
    }

    public String getDesc() {
        return "The average temperature in 4 Greek islands (line chart)";
    }
   @Override
    public Intent execute(Context context){
        return null;
    }

    public View executed(Context context) {
        String[] titles = new String[] { "鸡", "鸭", "鹅", "Skiathos" };//图例
        List<double[]> x = new ArrayList<double[]>();
        for (int i = 0; i < titles.length; i++) {
            x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });//每个序列中点的X坐标
        }
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
                13.9 });//序列1中点的y坐标
        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });//序列2中点的Y坐标
        values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });//序列3中点的Y坐标
        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });//序列4中点的Y坐标
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };//每个序列的颜色设置
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
                PointStyle.TRIANGLE, PointStyle.SQUARE };//每个序列中点的形状设置
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);//调用AbstractDemoChart中的方法设置renderer.

        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//设置图上的点为实心
        }

        // 图表部分的背景颜色
        renderer.setBackgroundColor(Color.parseColor("#f3f3f3"));
        renderer.setApplyBackgroundColor(true);
        // 图表与屏幕四边的间距颜色
        renderer.setMarginsColor(Color.argb(0, 0xF3, 0xF3, 0xF3));
        renderer.setChartTitleTextSize(30);
        renderer.setAxisTitleTextSize(25);
        // renderer.setLegendHeight(50);
        // 图例文字的大小
        renderer.setLegendTextSize(20);
        renderer.setMargins(new int[] { 50, 50, 50, 30 });
        // x、y轴上刻度颜色
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0, Color.BLACK);
        setChartSettings(renderer, "市场趋势", "Month", "价格走势", 0.5, 12.5, -10, 40,
                Color.BLACK, Color.BLACK);//调用AbstractDemoChart中的方法设置图表的renderer属性.
        renderer.setXLabels(12);//设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔
        renderer.setYLabels(10);//设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔
        renderer.setShowGrid(true);//是否显示网格
        renderer.setXLabelsAlign(Align.RIGHT);//刻度线与刻度标注之间的相对位置关系
        renderer.setYLabelsAlign(Align.CENTER);//刻度线与刻度标注之间的相对位置关系
       // renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮
        renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); //设置拖动时X轴Y轴允许的最大值最小值.
        renderer.setZoomLimits(new double[] {  -10, 20, -10, 40 });//设置放大缩小时X轴Y轴允许的最大最小值.
        View view = ChartFactory.getLineChartView(context, buildDataset(titles, x, values),
                renderer);//构建Intent
        return view;

    }
}
