package com.chen.chenzi.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 可以拿到一个文件的MD5加密值
 * @author 鄢家银
 *
 */
public class MD5Util {
	public static String getMD5(String input,String inputType){
		try {
			if (inputType.equals("file")) {
				return fileMD5(input);
			} else if (inputType.equals("string")) {
				return	getMD5(input);
			} else {
				System.out.println("输入类型错误，默认作为字符串");
				return getMD5(input);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String fileMD5(String inputFile) throws IOException {

		  // 缓冲区大小（这个可以抽出一个参数）

		  int bufferSize = 256 * 1024;


		  FileInputStream fileInputStream = null;

		  DigestInputStream digestInputStream = null;



		  try {

		     // 拿到一个MD5转换器（同样，这里可以换成SHA1）

		     MessageDigest messageDigest =MessageDigest.getInstance("MD5");



		     // 使用DigestInputStream

		     fileInputStream = new FileInputStream(inputFile);

		     digestInputStream = new DigestInputStream(fileInputStream,messageDigest);



		     // read的过程中进行MD5处理，直到读完文件

		     byte[] buffer =new byte[bufferSize];

		     while (digestInputStream.read(buffer) > 0);



		     // 获取最终的MessageDigest

		     messageDigest= digestInputStream.getMessageDigest();



		     // 拿到结果，也是字节数组，包含16个元素

		     byte[] resultByteArray = messageDigest.digest();



		     // 同样，把字节数组转换成字符串

		     return byteArrayToHex(resultByteArray);



		  } catch (NoSuchAlgorithmException e) {

		     return null;

		  } finally {

		     try {

		        digestInputStream.close();

		     } catch (Exception e) {

		     }

		     try {

		        fileInputStream.close();

		     } catch (Exception e) {

		     }

		  }
		}
	public static String getMD5(String input) throws NoSuchAlgorithmException{
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		byte[] inputByte=input.getBytes();
		messageDigest.update(inputByte);
		byte[] inputMD5=messageDigest.digest();
		
		
		
		return byteArrayToHex(inputMD5);
		
	}
	
	public static String byteArrayToHex(byte[] byteArray) {

		  // 首先初始化一个字符数组，用来存放每个16进制字符

		  char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };



		  // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

		  char[] resultCharArray =new char[byteArray.length * 2];



		  // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

		  int index = 0;

		  for (byte b : byteArray) {

		     resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];

		     resultCharArray[index++] = hexDigits[b& 0xf];

		  }



		  // 字符数组组合成字符串返回

		  return new String(resultCharArray);
		}
	
}
