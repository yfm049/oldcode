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
import com.microblog.po.User;


public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 6134014846518196144L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	
		request.setCharacterEncoding("UTF-8");

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

						System.out.println("普通控件 ， 名为: " + item.getFieldName() + "值为: " + item.getString("utf-8"));
					
					} else {
						// 获得获得文件名，此文件名包括路径
						String filename = item.getName();
						if (filename != null) {
							File file = new File(filename);
						
							File filetoserver = new File(this.getServletContext().getRealPath("/face"),file.getName());
								
							item.write(filetoserver);
							
							String u_pic =request.getContextPath()+"/face/"+ filename.substring(filename.lastIndexOf("\\")+1);
	
							System.out.println(u_pic);
					 		
							System.out.println("文件 " + filetoserver.getName() + " 上传成功!!");
							
							
							int u_id = Integer.parseInt(request.getParameter("u_id"));
						
							
							User user = new User();
							user.setU_pic(u_pic);
							user.setU_id(u_id);
							
							UserDao userDao = new UserDao();
							userDao.updatePic(user);
							
							HttpSession session = request.getSession();
							session.setAttribute("user",user);
							
						
						
							response.sendRedirect("../myface.jsp");
						}
					}
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
//	public static void main(String [] args){
////		String su="D:/face/su.jsp";
////		int s=su.lastIndexOf("/");
////		su=su.substring(s, su.length());
////		System.out.println(su);
////		
////	}

}
