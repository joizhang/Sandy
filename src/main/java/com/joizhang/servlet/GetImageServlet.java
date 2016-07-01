package com.joizhang.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.SmbFile;

public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
        String imageName = req.getParameter("imageUrl");
        System.out.print(imageName);
        SmbFile remoteFile = new SmbFile("smb://ZXL:zhangxinlin@//127.0.0.1/Users/Public");
        System.out.println(remoteFile.exists());
	}
}
