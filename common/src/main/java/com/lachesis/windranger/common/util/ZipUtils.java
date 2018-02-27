package com.lachesis.windranger.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 解压文件工具类
 * 
 * @author ThinkPad
 *
 */
public class ZipUtils {

	/**
	 * 将压缩文件解压至指定目录
	 * 
	 * @param zipFile
	 * @param unzipDir
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String unzip(File zipFile, String unzipDir) {
		String result = null;
		ZipFile zf=null;
		OutputStream out=null;
		try {
			 zf = new ZipFile(zipFile);
			Enumeration enu = zf.entries();
			while (enu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) enu.nextElement();
				String name = entry.getName();
				String path = unzipDir + name;
				result = result + path + "<br/>";
				File file = new File(path);
				if (entry.isDirectory()) {
					file.mkdirs();
				} else {
					InputStream is = zf.getInputStream(entry);
					byte[] buf1 = new byte[1024];
					int len;
					if (!file.exists()) {
						file.getParentFile().mkdirs();
						file.createNewFile();
					}
					 out = new FileOutputStream(file);
					while ((len = is.read(buf1)) > 0) {
						out.write(buf1, 0, len);
					}
				}
			}
			result = "文件解压成功,解压文件:<br/>" + result;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				zf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
