package com.sc.tradmaster.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SystemRecordUtils {
	public static void writeInfoTofile(String filePath,String content){
		 String str = new String(); //原有txt内容 
	        String s1 = new String();//内容更新 
	        try {  
	            File f = new File("c:/myClientFile/text.txt");
	            if (f.exists()) {  
	                //System.out.print("文件存在");  
	            } else {  
	                System.out.print("文件不存在");  
	                f.getParentFile().mkdirs();
	                f.createNewFile();// 不存在则创建
	            }  
	            BufferedReader input = new BufferedReader(new FileReader(f));  
	            while ((str = input.readLine()) != null) {  
	                s1 += str + "\r\n";  
	            }  
	            //System.out.println(s1);
	            input.close();  
	            s1 += content;  
	  
	            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
	            output.write(s1+"\r\n");
	            output.close();
	        } catch (Exception e) {
	            e.printStackTrace();  
	  
	        }  
	}
	
	public static void main(String[] args) {
		writeInfoTofile(null,"123456");
	}
}
