package com.microblog.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.po.User;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//从前台页面获取用户信息
		String u_account = user.getU_account();
		String u_pwd = user.getU_pwd();
		String u_nickname = user.getU_nickname();
		String u_sex = user.getU_sex();
		String w_content = request.getParameter("w_content");
		
		//获得输入流，该输出流的介质是客户端浏览器
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename = temp.xls");
		response.setContentType("application/msexcel");
		
		//创建可写入的excel工作表，内容写入到输入流，并通过输入流输出给客户端浏览器
		WritableWorkbook wk = Workbook.createWorkbook(output);
		WritableSheet sheet = wk.createSheet("用户信息表", 0);
		//列、行
		try {
			//把单元格（行、列）合并
			sheet.mergeCells(0,0, 4,0);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//设置字体
		WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.LIGHT_BLUE);
		
	   WritableCellFormat titleFormat = new WritableCellFormat();
	   titleFormat.setFont(titleFont);
	   try {
		   //水平居中
		titleFormat.setAlignment(Alignment.CENTRE);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   
	try {
		//垂直居中
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		//背景颜色
		titleFormat.setBackground(Colour.GRAY_25);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		//自动换行
		titleFormat.setWrap(true);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//添加label对象，参数以此表示在第一行、第一列、内容、使用格式
	Label lab_00 = new Label(0,0,"用户信息", titleFormat);
	try {
		
		//添加好的label对象添加到工作表上，
		sheet.addCell(lab_00);
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	WritableCellFormat columnTitleFormat = new WritableCellFormat();
	columnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false));
	
	try {
		columnTitleFormat.setAlignment(Alignment.CENTRE);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	Label lab_01 = new Label(0,1,"用户名",columnTitleFormat);
	Label lab_11 = new Label(1,1,"密码",columnTitleFormat);
	Label lab_21 = new Label(2,1,"昵称",columnTitleFormat);
	Label lab_31 = new Label(3,1,"性别",columnTitleFormat);
	Label lab_41 = new Label(4,1,"微博",columnTitleFormat);
	
	try {
		sheet.addCell(lab_01);
		sheet.addCell(lab_11);
		sheet.addCell(lab_21);
		sheet.addCell(lab_31);
		sheet.addCell(lab_41);
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	try {
		sheet.addCell(new Label(0,2,u_account));
		sheet.addCell(new Label(1,2,u_pwd));
		sheet.addCell(new Label(2,2,u_nickname));
		sheet.addCell(new Label(3,2,u_sex));
		sheet.addCell(new Label(4,2,w_content));
		
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//将工作表定义的工作表输出到客户端浏览器
	wk.write();
	try {
		//关闭对象
		wk.close();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	}
	
}
