package com.microblog.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

import com.microblog.dao.BallotDao;

public class ShowBallotServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BallotDao ballotDao = new BallotDao();

		double rs1 = ballotDao.showSelect1();
		double rs2 = ballotDao.showSelect2();
		double rs3 = ballotDao.showSelect3();
		double rs4 = ballotDao.showSelect4();
		double rs5 = ballotDao.showSelect5();

		response.setContentType("text/html;charset=utf-8");

		double[][] data = new double[][] { { rs1 }, { rs2 }, { rs3 }, { rs4 }, { rs5 } };

		String[] rowKeys = { "费德勒", "纳达尔", "德约科维奇", "罗迪克", "穆雷" };

		String[] columnKeys = { "" };

		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data);

		Font font = new Font("宋体", Font.BOLD, 16);

		
		
		JFreeChart chart = ChartFactory.createBarChart3D("用户投票", "运动员",
				"用户数", dataset, PlotOrientation.VERTICAL, true, false, false);
		CategoryPlot plot = chart.getCategoryPlot();
		TextTitle title = new TextTitle("用户投票", font);
		

		TextTitle subtitle = new TextTitle("美网竞猜", new Font("黑体", Font.BOLD,
				12));
		
		chart.addSubtitle(subtitle);

		chart.setTitle(title); //标题
		
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();

		CategoryAxis domainAxis = plot.getDomainAxis();

		/*------设置X轴坐标上的文字-----------*/

		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));

		/*------设置X轴的标题文字------------*/

		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		

		/*------设置Y轴坐标上的文字-----------*/

		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));

		/*------设置Y轴的标题文字------------*/

		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));

		/*------这句代码解决了底部汉字乱码的问题-----------*/

		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
		
		
		//设置网格背景颜色

		plot.setBackgroundPaint(Color.white);

		//设置网格竖线颜色

		plot.setDomainGridlinePaint(Color.pink);

		//设置网格横线颜色

		plot.setRangeGridlinePaint(Color.pink);
		
		
		//显示每个柱的数值，并修改该数值的字体属性

		BarRenderer3D renderer = new BarRenderer3D();

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());

		renderer.setBaseItemLabelsVisible(true);
		
		//默认的数字显示在柱子中，通过如下两句可调整数字的显示

		//注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题

		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));

		renderer.setItemLabelAnchorOffset(10D);

		renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));

		renderer.setItemLabelsVisible(true);
		
		//设置每个地区所包含的平行柱的之间距离

		//renderer.setItemMargin(0.2);

		plot.setRenderer(renderer);
		
		
		
		String filename = ServletUtilities.saveChartAsPNG(chart, 550, 350,
				null, request.getSession());
		
		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;
		
		request.setAttribute("graphURL",graphURL);
		request.setAttribute("filename", filename);
		
		request.getRequestDispatcher("/sample.jsp").forward(request, response);
		
	}

}
