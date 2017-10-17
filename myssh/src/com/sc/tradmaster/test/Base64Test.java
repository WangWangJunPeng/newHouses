package com.sc.tradmaster.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SysContent;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Test {
	public static void main(String[] args) throws Exception {
		// 测试从图片文件转换为Base64编码
		String base64 = GetImageStr("D:\\tools\\mastor\\upload\\imgs\\20170321140413878.jpg");
		System.out.println(base64);

		// 测试从Base64编码转换为图片文件
		String strImg = "这里放64位编码";
		GenerateImage(base64, "appUploadPic.jpg");
		
	} 
	  
	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
		byte[] data = null;  
		// 读取图片字节数组  
		try {  
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];  
			in.read(data);  
			in.close();  
		} catch (IOException e) {
			e.printStackTrace();  
		}  
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();  
		return encoder.encode(data);//返回Base64编码过的字节数组字符串
	}  
	  
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片  
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码,将imgStr转成bytes数组
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}  
	}  
}