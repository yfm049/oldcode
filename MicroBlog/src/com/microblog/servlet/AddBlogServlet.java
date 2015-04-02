package com.microblog.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.microblog.dao.UserDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class AddBlogServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//从前台获得数据
		//int w_id = Integer.parseInt(request.getParameter("w_id"));
		//String w_content = request.getParameter("blogtext");
		//String w_unick = request.getParameter("blogunick");
		//String w_upic = request.getParameter("w_upic");
		String w_image = request.getParameter("upfile");
		
		Weibo weibo = new Weibo();
		
		
	
	try {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileload = new ServletFileUpload(factory);
		
		// 设置最大文件尺寸，这里是4MB
		fileload.setSizeMax(4194304);
		
		List<FileItem> files = fileload.parseRequest(request);
		
		//循环迭代分析每一个表单项
		Iterator<FileItem> iterator = files.iterator();
		
		while (iterator.hasNext()) {
			
			FileItem item = iterator.next();
			
			//如果当前项是普通表单项。
			if (item.isFormField()) { 
				if("blogtext".equals(item.getFieldName())){
					weibo.setW_content(item.getString("utf-8"));
					}
				if("w_id".equals(item.getFieldName())){
					weibo.setW_id(Integer.parseInt(item.getString("utf-8")));
					}
				
				if("w_unick".equals(item.getFieldName())){
					weibo.setW_unick((item.getString("utf-8")));
					}
				
				if("w_upic".equals(item.getFieldName())){
					weibo.setW_upic((item.getString("utf-8")));
					}
				
				
				
				
				System.out.println("普通控件 ， 名为: " + item.getFieldName() + "值为: " + item.getString("utf-8"));
			
			} else {
				// 获得获得文件名，此文件名包括路径
				String filename = item.getName();

				if (filename!="") {
					File file = new File(filename);
				
					File filetoserver = new File(this.getServletContext().getRealPath("/upload/pic"),file.getName());
						
					item.write(filetoserver);
					
					 w_image =request.getContextPath()+"/upload/pic/"+ filename.substring(filename.lastIndexOf("\\")+1);

					System.out.println(w_image);
			 		
					System.out.println("文件 " + filetoserver.getName() + " 上传成功!!");
					
					//处理数据,封装数据传到dao
					
					
					
					
					//weibo.setW_unick(w_unick);
					//weibo.setW_upic(w_upic);
					weibo.setW_image(w_image);
					
					
					//调用dao
					WeiboDao weiboDao = new WeiboDao();
					boolean flag = weiboDao.sendWeibo(weibo);
					int res = flag?1:2;
					
					//页面跳转
					response.sendRedirect("../servlet/AllServlet?msg="+res);
				}else{
					WeiboDao weiboDao = new WeiboDao();
					boolean flag = weiboDao.sendWeibo2(weibo);
					int res = flag?1:2;
		
					//页面跳转
					response.sendRedirect("../servlet/AllServlet?msg="+res);
					
					
				}
			}
			
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}


	}
	}
