package com.microblog.jfc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class Pie {
	public static void main(String[] args) {
		//数据
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("中文", 43.2);
		dataset.setValue("Category 2", 27.9);
		dataset.setValue("Category 3", 79.5);
		dataset.setValue("Category 4", null);
		dataset.setValue("Category 5", 0);
		dataset.setValue("Category 6", -50.0f);
		//创建 Jfreechart对象
		JFreeChart jfreechart = ChartFactory.createPieChart("Simple Pie Chart", dataset, true, true, false);
		PiePlot piePlot = (PiePlot) jfreechart.getPlot();
		//指定分类饼的颜色
		piePlot.setSectionPaint("中文",Color.blue);
		piePlot.setSectionPaint("Category 2",Color.red);
		piePlot.setSectionPaint("Category 3",Color.yellow);
		
		//设置Pie的边框是否可见
		piePlot.setSectionOutlinesVisible(true);
		// 指定图片的透明度(0.0-1.0)
		piePlot.setForegroundAlpha(1.0f);
		//设置边框的颜色
		piePlot.setBaseSectionOutlinePaint(Color.green);
		//设置边框的粗细,new BasicStroke(2.0f)
		piePlot.setBaseSectionOutlineStroke(new BasicStroke(1));
		//设置空值,0值,负值是否显示出来,如果显示的话就是false
		piePlot.setIgnoreNullValues(true);
		piePlot.setIgnoreZeroValues(true);
		
		//设置上面的样式,0表示KEY,1表示VALUE,2表示百分之几,DecimalFormat用来显示百分比的格式
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}=>{1}({2})",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
		
		//设置下面方框的样式,0表示KEY,1表示VALUE,2表示百分之几,DecimalFormat用来显示百分比的格式
		piePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}=>{1}({2})",NumberFormat.getNumberInstance(),	new DecimalFormat("0.00%")));
		
		//爆炸模式,使Pie的一块分离出去,不支持3D
		piePlot.setExplodePercent("Category 1", 0.10);
		piePlot.setExplodePercent("Category 2", 0.10);
		//piePlot.setExplodePercent("Category 3", 0.10);
		
		//定义字体格式
		Font font = new Font("微软雅黑", Font.CENTER_BASELINE, 12);
		//定义图片标题
		TextTitle title = new TextTitle("Pie状图");
		//设置标题的格式
		title.setFont(font);
		//把标题设置到图片里面
		jfreechart.setTitle(title);
		
		//设置字体,非常关键不然会出现乱码的,下方的字体
		jfreechart.getLegend().setItemFont(font);
		//Pie图的字体
		piePlot.setLabelFont(font);
		
		//创建输出流
		FileOutputStream fos_jpg = null;
		try {			
			fos_jpg = new FileOutputStream("c:\\Pie.jpg");
			//用工具把图象写到硬盘,支持两种格式,JPG,PNG,还支持MAP
			ChartUtilities.writeChartAsJPEG(fos_jpg,0.99f,jfreechart,640,480,null);
			fos_jpg.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
