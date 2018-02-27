package com.lachesis.windranger.common.util;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
	public static final char UNDERLINE = '_';

	public static String trim(String input, int length) {
		return trim(input, length, true);
	}

	/**
	 * @param input
	 * @param length
	 * @param withEllipsis
	 * @return
	 */
	public static String trim(String input, int length, boolean withEllipsis) {
		if (input == null) {
			return "";
		}

		if (input.length() <= length)
			return input;

		return (withEllipsis) ? (input.substring(0, length) + "...") : (input.substring(0, length));
	}

	/**
	 * Check whether <code>text</code> is an Ascii string
	 *
	 * @param text
	 * @return
	 */
	public static boolean isAsciiString(String text) {
		return text.matches("\\A\\p{ASCII}*\\z");
	}

	public static String generateSoftUniqueId() {
		return "" + (new GregorianCalendar().getTimeInMillis()) + new Random().nextInt(10);
	}

	public static String getStrOptionalNullValue(String value) {
		return (value == null) ? "" : value;
	}

	public static String extractNameFromEmail(String value) {
		int index = (value != null) ? value.indexOf("@") : 0;
		if (index > 0) {
			return value.substring(0, index);
		} else {
			return value;
		}
	}

	public static boolean isValidPhoneNumber(String value) {
		if (value != null && !value.trim().equals("")) {

			// validate phone numbers of format "1234567890"
			if (value.matches("\\d{10}"))
				return true;
			// validating phone number with -, . or spaces
			else if (value.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
				return true;
			// validating phone number with extension length from 3 to 5
			else if (value.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
				return true;
			// validating phone number where area code is in braces ()
			else if (value.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
				return true;
			// return false if nothing matches the input
			else
				return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断字符串是否不为null或去空格后长度为0
	 *
	 * @param s
	 *            字符串
	 * @return true，不为null或去空格后长度为0
	 */
	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	/**
	 * 判断至少一个字符串是否不为null或去空格后长度为0
	 *
	 * @param ss
	 *            字符串数组
	 * @return 如果至少一个字符串不为null或去空格后长度为0，则返回true。
	 */
	public static boolean isNotEmptyAny(String... ss) {
		for (String s : ss) {
			if (isNotEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断所有字符串是否不为null或去空格后长度为0
	 *
	 * @param ss
	 *            字符串数组
	 * @return 如果所有字符串不为null或去空格后长度为0，则返回true。
	 */
	public static boolean isNotEmptyAll(String... ss) {
		for (String s : ss) {
			if (isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 把一个对象数组用分隔字符串连接成一个字符串。
	 *
	 * @param objs
	 *            对象数组
	 * @param splitString
	 *            分割字符串
	 * @return 连接后的字符串
	 */
	public static <T> String join(T[] objs, String splitString) {
		StringBuilder s = new StringBuilder();
		if (objs.length > 0) {
			s.append(objs[0]);
			for (int i = 1; i < objs.length; i++) {
				s.append(splitString).append(objs[i]);
			}
		}

		return s.toString();
	}

	/**
	 * 把一个对象列表用分隔字符串连接成一个字符串。
	 *
	 * @param objList
	 *            对象列表
	 * @param splitString
	 *            分割字符串
	 * @return 连接后的字符串
	 */
	public static String join(List<?> objList, String splitString) {
		StringBuilder s = new StringBuilder();
		if (objList.size() > 0) {
			s.append(objList.get(0));
			for (int i = 1, ii = objList.size(); i < ii; i++) {
				s.append(splitString).append(objList.get(i));
			}
		}

		return s.toString();
	}

	/**
	 * 把一个对象数组的列表的某一列数据用分隔字符串连接成一个字符串。
	 *
	 * @param objList
	 *            对象数组的列表
	 * @param splitString
	 *            分割字符串
	 * @return 连接后的字符串
	 */
	public static <T> String join(List<T[]> objList, int columnIndex, String splitString) {
		StringBuilder s = new StringBuilder();
		if (objList.size() > 0) {
			s.append(objList.get(0)[columnIndex]);
			for (int i = 1, ii = objList.size(); i < ii; i++) {
				s.append(splitString).append(objList.get(i)[columnIndex]);
			}
		}

		return s.toString();
	}

	/**
	 * 判断字符串是否为null或去空格后长度为0
	 *
	 * @param s
	 *            字符串
	 * @return true，为null或去空格后长度为0
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * 是否是邮件地址格式
	 *
	 * @param s
	 *            字符串
	 * @return true，是
	 */
	public static boolean isEmail(String s) {
		return s.matches("^[a-zA-Z0-9._-]+@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)+$");
	}

	/**
	 * 是否是手機號碼格式
	 *
	 * @param s
	 *            字符串
	 * @return true，是
	 */
	public static boolean isMobile(String s) {
		if (isEmpty(s)) {
			return false;
		}
		// 增加虚拟运营商号码段..
		return s.matches("^1[34578]\\d{9}$");
	}

	/**
	 * 创建32位UUID
	 *
	 * @return true，是
	 */
	public static String getTWFormatUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String encryptMobile(String mobile) {
		if (mobile == null) {
			return null;
		}
		int encryptLength = mobile.length() > 4 ? mobile.length() - 4 : mobile.length() / 2;
		StringBuilder sb = new StringBuilder(mobile.length());
		for (int i = 0; i < encryptLength; i++) {
			sb.append("*");
		}
		sb.append(mobile.substring(encryptLength));
		return sb.toString();
	}

	public static String encryptEmail(String email) {
		if (email == null) {
			return null;
		}

		String[] split = email.split("@");
		if (split.length != 2) {
			return null;
		}

		String username = split[0];
		String mailTail = split[1];

		int encryptLength = username.length() > 4 ? username.length() - 4 : username.length() / 2;
		encryptLength = encryptLength == 0 ? 1 : encryptLength;

		StringBuilder sb = new StringBuilder(username.length());

		sb.append(username.substring(0, username.length() - encryptLength));

		for (int i = 0; i < encryptLength; i++) {
			sb.append("*");
		}
		sb.append("@");
		sb.append(mailTail);

		return sb.toString();

	}

	/**
	 * @功能 将英文字符串首字母转为大写
	 * @param str
	 *            要转换的字符串
	 * @return String 型值
	 */
	public static String toUpCaseFirst(String str) {
		if (str == null || "".equals(str)) {
			return str;
		} else {
			char[] temp = str.toCharArray();
			temp[0] = str.toUpperCase().toCharArray()[0];
			str = String.valueOf(temp);
		}

		return str;
	}

	public static String polishZero(String str) {
		// if (str.length() >= 32)
		// return str;
		// String polish = "00000000000000000000000000000000";
		// return polish.substring(str.length()) + str;

		return polishZero(str, 32);
	}

	public static String polishZero(String str, int length) {
		if (str.length() >= length)
			return str;
		int size = length - str.length();
		StringBuffer polish = new StringBuffer();
		for (int i = 0; i < size; i++) {
			polish.append("0");
		}
		return polish + str;
	}

	/**
	 * Mac 格式校验
	 *
	 * @param mac
	 * @return
	 */
	public static Boolean isMac(String mac) {
		if (isEmpty(mac)) {
			return false;
		}

		Pattern p = Pattern.compile("^[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}$");

		Matcher m = p.matcher(mac);

		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 产生四位数字的验证码
	 * 
	 * @return
	 */
	public static String createMobileCode() {
		return createNumberCodeCount(4);
	}

	/**
	 * 产生多少随机的数字字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String createNumberCodeCount(int count) {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			code = code + random.nextInt(10);
		}
		return code;
	}

	/**
	 * 是否是身份证号码
	 * 
	 * @param cardNumber
	 * @return
	 */
	public static boolean isCardNumber(String cardNumber) {
		if (StringUtils.isEmpty(cardNumber)) {
			return false;
		}
		String reg = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
		return cardNumber.matches(reg);
	}

	/**
	 * 是否是金额数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBigDecimal(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isBigDecimal = pattern.matcher(str);
		if (!isBigDecimal.matches()) {
			if (str.trim().indexOf(".") == -1) {
				pattern = java.util.regex.Pattern.compile("^[+-]?[0-9]*");
				isBigDecimal = pattern.matcher(str.trim());
			} else {
				pattern = java.util.regex.Pattern.compile("^[+-]?[0-9]+(\\.\\d{1,100}){1}");
				isBigDecimal = pattern.matcher(str.trim());
			}
		}
		return isBigDecimal.matches();
	}

	public static boolean equals(String s1, String s2) {
		boolean rs = true;
		if (isEmpty(s1)) {
			if (!isEmpty(s2)) {
				rs = false;
			}
		} else if (!s1.equals(s2)) {
			rs = false;
		}
		return rs;
	}

	/**
	 * 是否为数字
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isANumber(String value) {
		if (StringUtils.isEmpty(value))
			return false;
		Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean intToBoolean(Integer i) {
		return i == 1 ? true : false;
	}

	public static int booleanToInt(boolean bo) {
		return bo == true ? 1 : 0;
	}

	/**
	 * 
	 * @Title: camelToUnderline 
	 * @Description: 驼峰转下划线
	 * @param param 驼峰形式
	 * @return String 下划线形式 
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * @Title: underlineToCamel 
	 * @Description: 下划线转驼峰形式 
	 * @param param 下划线形式
	 * @return String 驼峰形式
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
}
