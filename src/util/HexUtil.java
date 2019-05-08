package util;

import java.io.UnsupportedEncodingException;

/**
 * Hex输入输出转换
 *
 * @author Rui
 * @version 0.3 (2017-4-7)
 */
public final class HexUtil {
	public static void out(byte[] data) {
		if (null == data || data.length == 0) {
			System.out.println();
		} else {
			for (byte aData : data) {
				String hex = Integer.toHexString(aData & 0xFF);
				if (hex.length() == 1) {
					hex = '0' + hex;
				}
				System.out.print(hex.toUpperCase());
			}
			System.out.println();
		}
	}

	/**
	 * 将Hex字符串转换为byte数组
	 *
	 * @param hex 16进制字符串
	 */
	public static byte[] Hexs2Bytes(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException("Hex串长度必须是2的整数倍");
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	/**
	 * 将byte数组转换为Hex字符串
	 */
	public static String Bytes2Hexs(byte[] b) {
		String hs = "";
		String stmp = "";
		for (byte aB : b) {
			stmp = (Integer.toHexString(aB & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 将HexString转换为ISO-8859-1 编码的字符串
	 */
	public static String HexString2String(String hexs) throws UnsupportedEncodingException {
		return new String(Hexs2Bytes(hexs), "ISO-8859-1");
	}

	/**
	 * 将ISO-8859-1 编码的字符串转换为HexString
	 */
	public static String String2HexString(String isoString) throws UnsupportedEncodingException {
		return Bytes2Hexs(isoString.getBytes("ISO-8859-1"));
	}
}