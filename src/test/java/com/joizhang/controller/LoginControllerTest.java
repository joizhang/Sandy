package com.joizhang.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.validation.constraints.AssertTrue;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.junit.Test;


public class LoginControllerTest {

	@Test
	public void test() throws SmbException{
		try {
			InputStream inputStream = new FileInputStream(new File("z:/work/00009_1.jpg"));
			System.out.println(inputStream.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
