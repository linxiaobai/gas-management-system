package com.gms.util.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;

import java.awt.*;


/**
 * Created by Kevin on 2015/5/10.
 */
public class JFreeChartUtils {
    /*图表类型*/
    public static final Integer BAR_CHART = 1;
    public static final Integer LINE_CHART = 2;

    /**
     * 纵坐标为浮点型数值
     * @param dataSet
     * @param title
     * @param categoryAxisName
     * @param valueAxisName
     * @return
     */
    public static JFreeChart createBarChart(CategoryDataset dataSet, String title, String categoryAxisName, String valueAxisName)  {
        JFreeChart chart= ChartFactory.createBarChart(title, categoryAxisName,
                valueAxisName, dataSet, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle(title, new Font("宋体", Font.BOLD + Font.ITALIC, 20)));//重新设置标题样式
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis =plot.getDomainAxis();//获得横坐标
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        return chart;
    }

    /**
     * 纵坐标为整型
     * @param dataSet
     * @param title
     * @param categoryAxisName
     * @param valueAxisName
     * @return
     */
    public static JFreeChart createBarChartInt(CategoryDataset dataSet, String title, String categoryAxisName, String valueAxisName) {
        JFreeChart chart= ChartFactory.createBarChart(title, categoryAxisName,
                valueAxisName, dataSet, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle(title, new Font("宋体", Font.BOLD + Font.ITALIC, 20)));//重新设置标题样式
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis =plot.getDomainAxis();//获得横坐标
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoTickUnitSelection(false);//数据轴的数据标签是否自动确定（默认为true）
        rangeAxis.setStandardTickUnits(rangeAxis.getStandardTickUnits());//数据轴的数据标签
        rangeAxis.setLowerBound(0.0); //数据轴上的显示最小值;
        rangeAxis.setAutoRangeMinimumSize(1.0);//1为一个间隔单位
        plot.setRangeAxis(rangeAxis);
        return chart;
    }

    public static JFreeChart createLineChart(CategoryDataset dataSet, String title, String categoryAxisName, String valueAxisName) {
        JFreeChart chart= ChartFactory.createLineChart(title, categoryAxisName,
                valueAxisName, dataSet, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle(title, new Font("宋体", Font.BOLD + Font.ITALIC, 20)));//重新设置标题样式
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis =plot.getDomainAxis();//获得横坐标
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        return chart;
    }
}
