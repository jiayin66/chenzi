package com.chen.chenzi.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来把txt文本中指定xx字符与x字符之间的抽取出来存到list集合中。 缺点： 1.能提取英文，数字，可以提取中文。
 * 2.结束字符目前只设计了一个，因为一般一个就好啦。如果需要多个，再修改代码也很简单。
 * 
 * @author 鄢家银 20180619
 */
public class ExtractCharYJY {
	/**
	 * 只需要三个参数，start stop path
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String start = "lxq\": \"";
		String stop = "\"";
		String path = "C:/Users/yjy/Desktop/新建文件夹/idlinshi1.txt";
		System.out.println("您输入的开始部分为:" + start + "结束部分为:" + stop + "路径" + path);

		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		BufferedReader br = new BufferedReader(isr);


		String[] startArr = start.split("");
		String[] stopArr = stop.split("");

		// 装数据的集合
		List<StringBuilder> arr = new ArrayList<StringBuilder>();
		int str;
		// 循环读取
		while ((str = br.read()) != -1) {
			ok:
			// 如果满足开始字符
			if (str == startArr[0].codePointAt(0)) {
				for (int i = 0; i < startArr.length && str != -1; i++, str = br.read()) {
					if (str != startArr[i].codePointAt(0)) {
						// 后续字符不匹配就直接跳到主循环
						break ok;
					}
				}
				StringBuilder sb = new StringBuilder();
				sb.append((char) str);
				while ((str = br.read()) != -1) {
					// 到达底部就直接跳到主循环
					if (str == stopArr[0].codePointAt(0)) {
						break;
					}

					sb.append((char) str);

				}
				arr.add(sb);
				System.out.println(sb);

			}

		}
		System.out.println(arr);
		br.close();


	}
}
