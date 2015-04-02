package com.microblog.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.microblog.dao.ImporExcelDao;
import com.microblog.po.User;

public class ImportServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//将xls上传到服务器下的一个地址
		String url = request.getParameter("importfile");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(20 * 1024 * 1024); // 20M
		factory.setRepository(new File(request.getRealPath("/upload/xls_file")));

		ServletFileUpload handler = new ServletFileUpload(factory);
		handler.setSizeMax(3 * 1024 * 1024);
		handler.setHeaderEncoding("utf-8");

		try {
			List<FileItem> files = handler.parseRequest(request);
			Iterator<FileItem> it = files.iterator();

			while (it.hasNext()) {
				FileItem item = it.next();
				if (item.isFormField()) {
				} else {
					
					String filename = item.getName();
					File file = new File(filename);
					File filetoserver = new File(
							request.getRealPath("/upload/xls_file"),
							file.getName());
					item.write(filetoserver);

					String filetodb = request.getContextPath()
							+ "/upload/xls_file/"
							+ filename.substring(filename.lastIndexOf("\\") + 1);
					
					

					//生成集合
					List temp = loadUserInfo(request.getRealPath("/upload/xls_file")+"/"+file.getName());
					//将集合插入excel表
					ImporExcelDao importExcelDao = new ImporExcelDao();

				
					boolean flag = importExcelDao.importExcel(temp);
					int res = flag?1:2;
					response.sendRedirect("../into.jsp?msg="+res);
				}

			}
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public List<User> loadUserInfo(String xlsPath) throws IOException,
			BiffException {

		List temp = new ArrayList<User>();

		FileInputStream fis = new FileInputStream(xlsPath);

		Workbook wk = Workbook.getWorkbook(fis);
		// 获取第一张表
		Sheet sheet = wk.getSheet(0);
		// 获取总行数
		int rowNum = sheet.getRows();
		// 从数据行开始迭代
		for (int i = 2; i < rowNum; i++) {

			User user = new User();

			user.setU_account(sheet.getCell(0, i).getContents());
			user.setU_pwd(sheet.getCell(1, i).getContents());
			user.setU_nickname(sheet.getCell(2, i).getContents());
			user.setU_sex(sheet.getCell(3, i).getContents());

			temp.add(user);
		}

		fis.close();
		wk.close();
		return temp;

	}
}
