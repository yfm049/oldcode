package com.microblog.servlet;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.microblog.dao.BallotDao;

public class ShowBallot2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BallotDao ballotDao = new BallotDao();

		double sum = ballotDao.showSelectSum();
		double rs6 = ballotDao.showSelect6();
		double rs7 = ballotDao.showSelect7();
		double rs8 = ballotDao.showSelect8();
		double rs9 = ballotDao.showSelect9();
		double rs10 = ballotDao.showSelect10();

		double part1 = rs6 / sum;
		double part2 = rs7 / sum;
		double part3 = rs8 / sum;
		double part4 = rs9 / sum;
		double part5 = rs10 / sum;

		response.setContentType("text/html;charset=gbk");

		// 设置数据集
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("加勒比海盗", part1);
		dataset.setValue("功夫熊猫2", part2);
		dataset.setValue("哈利波特7", part3);
		dataset.setValue("变形金刚3", part4);
		dataset.setValue("不爱看电影……爱看裸婚时代", part5);

		// 通过工厂类生成JFreeChart对象
		JFreeChart chart = ChartFactory.createPieChart3D("最喜爱的暑期档影视作品",
				dataset, true, true, false);

		chart.getTitle().setFont(new Font("微软雅黑", Font.BOLD, 20));

		chart.getLegend().setItemFont(
				new Font("微软雅黑", Font.LAYOUT_LEFT_TO_RIGHT, 10));

		// 获得3D的水晶饼图对象
		PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot();

		chart.getTitle().setFont(new Font("微软雅黑", Font.BOLD, 20));
		pieplot3d.setLabelFont(new Font("微软雅黑", 0, 12));

		chart.getLegend().setItemFont(
				new Font("微软雅黑", Font.LAYOUT_LEFT_TO_RIGHT, 10));

		pieplot3d.setLabelFont(new Font("微软雅黑", 0, 12));

		// 设置开始角度
		pieplot3d.setStartAngle(150D);
		// 设置方向为”顺时针方向“
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		// 设置透明度，0.5F为半透明，1为不透明，0为全透明
		pieplot3d.setForegroundAlpha(0.5F);
		pieplot3d.setNoDataMessage("无数据显示");

		String filename = ServletUtilities.saveChartAsPNG(chart, 550, 350,
				null, request.getSession());

		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;

		request.setAttribute("graphURL", graphURL);
		request.setAttribute("filename", filename);

		request.getRequestDispatcher("/sample.jsp").forward(request, response);

	}

}
