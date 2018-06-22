package com.chen.chenzi.util;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * 可以删除文件夹下的重复文件，原理是通过MD5加密
 * 只需要一个参数，就是文件的目录。
 * @author yjy
 *
 */
public class DeleteRepeatImage {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		File file=new File("D:/weixin/WeChat Files/jiayinweixin520/Video");
		File[] fileArray=file.listFiles();
		System.out.println("总文件数为:"+fileArray.length);
		Set<String> set=new HashSet<String>();
		int x=0;
		for(int i=0;i<fileArray.length;i++){
			//System.out.println(fileArray[i].getAbsolutePath());
			//String str=TestFile.fileMD5(fileArray[i].getAbsolutePath());
			String str=MD5Util.getMD5(fileArray[i].getAbsolutePath(), "file");
			set.add(str);
			x++;
			if(set.size()<x){
				//System.out.println("发现重复照片");
				fileArray[i].delete();
				x--;
			}
			
		}
		System.out.println((fileArray.length-set.size())+"个重复文件已经删除！");
	}
}
