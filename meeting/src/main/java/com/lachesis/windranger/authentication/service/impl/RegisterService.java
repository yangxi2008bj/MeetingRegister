package com.lachesis.windranger.authentication.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.text.AbstractDocument.BranchElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lachesis.windranger.authentication.dao.AccountMapper;
import com.lachesis.windranger.authentication.dao.RegisterInfoMapper;
import com.lachesis.windranger.authentication.dbmodel.Account;
import com.lachesis.windranger.authentication.dbmodel.RegisterInfo;
import com.lachesis.windranger.authentication.model.ResponseJson;
import com.lachesis.windranger.authentication.sdk.utils.CheckSumBuilder;
import com.lachesis.windranger.authentication.service.IRegisterService;

@Service
public class RegisterService implements IRegisterService {
	@Autowired
	RegisterInfoMapper registerInfoMapper;
	@Autowired
	AccountMapper accountMapper;

	// 发送通知类信息URL
	private static final String SERVER_URL_INFORM =
					"https://api.netease.im/sms/sendtemplate.action";
	// 发送验证码URL
	private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";
	// 网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
	private static final String APP_KEY = "cdb6f718ebce8901a7c513f325c6ab19";
	// 网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
	private static final String APP_SECRET = "bc82fff94945";
	// 短信模板ID
	private static final String TEMPLATEID = "3882309";

	// 验证码长度，范围4～10，默认为4
	private static final String CODELEN = "6";

	@Override
	public Account insertorUpdateAccount(Account account) {
		List<Account> res = accountMapper.selectByBean(account);
		if (res != null && !CollectionUtils.isEmpty(res)) {
			return res.get(0);
		} else {
			accountMapper.insert(account);
			return accountMapper.selectByBean(account).get(0);
		}
	}

	@Override
	public boolean insertorUpdateRegisterInfo(RegisterInfo registerInfo) {
		RegisterInfo amount = new RegisterInfo();
		String season = registerInfo.getRegisterMeeting();
		amount.setRegisterMeeting(season);
		List<RegisterInfo> limit = registerInfoMapper.selectByBean(amount);

		RegisterInfo queryInfo = new RegisterInfo();
		queryInfo.setName(registerInfo.getName());
		queryInfo.setMobile(registerInfo.getMobile());
		List<RegisterInfo> res = registerInfoMapper.selectByBean(queryInfo);
		if (res != null && !CollectionUtils.isEmpty(res)) {
			if ((season.equals("1") && limit.size() > 1000)
							|| (season.equals("2") && limit.size() > 1200)
							|| (season.equals("3") && limit.size() > 1200)
							|| (season.equals("3B") && limit.size() > 500)
							|| (season.equals("4A") && limit.size() > 1200)
							|| (season.equals("4B") && limit.size() > 600)) {
				return false;
			}
			registerInfo.setSeqId(res.get(0).getSeqId());
			registerInfoMapper.updateByPrimaryKeySelective(registerInfo);
		} else {
			if (season.equals("3B") && limit.size() <= 500) {
				registerInfoMapper.insert(registerInfo);
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<RegisterInfo> getRegisterInfo(long registerId) {
		RegisterInfo queryInfo = new RegisterInfo();
		queryInfo.setRegisterId(registerId);
		return registerInfoMapper.selectByBean(queryInfo);
	}

	@Override
	public Map<String, Object> accountLogin(String mobile, String verification) {
		Map<String, Object> map = new HashMap<>();

		Account account = new Account();
		account.setName(mobile);
		account.setMobile(mobile);

		List<Account> res = accountMapper.selectByBean(account);
		if (res == null || CollectionUtils.isEmpty(res)
						|| !res.get(0).getVerification().equals(verification)) {
			map.put("account", new Account());
			map.put("verification", false);
		} else {
			map.put("account", res.get(0));
			map.put("verification", true);
		}

		return map;
	}

	@Transactional
	@Override
	public void sendVerification(String mobile) {

		Account account = new Account();
		account.setName(mobile);
		account.setMobile(mobile);

		if (mobile == null || mobile.trim().length() != 11) {
			return;
		}
		List<Account> res = accountMapper.selectByBean(account);

		try {
			String nonce = sendCode(mobile);
			if (nonce == null || nonce.trim().length() == 0) {
				return;
			}

			if (res != null && !CollectionUtils.isEmpty(res)) {
				account = res.get(0);
				account.setVerification(nonce);
				accountMapper.updateByPrimaryKeySelective(account);
			} else {
				account.setVerification(nonce);
				accountMapper.insert(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Override
	// public void sendInfo() {
	//
	// }

	public static void main(String[] args) throws Exception {
//		//第一期
//		String pathFirst = "F:\\send\\fisrt.txt";
//		List<String> first = send(pathFirst);
//		List<String> tmp = new ArrayList<>();
//		
//		for(int i = 0; i < first.size(); i++) {
//			tmp.add(first.get(i));
//			if(i%90==0){
//				System.out.println(tmp.get(0));
//				sendInform(tmp, "3952344");
//				tmp = new ArrayList<>();
//			}
//		}
//		System.out.println(tmp.get(tmp.size()-1));
//		sendInform(tmp, "3952344");

//		//第二期
//		String pathFirst = "F:\\send\\second.txt";
//		List<String> first = send(pathFirst);
//		List<String> tmp = new ArrayList<>();
//		
//		for(int i = 0; i < first.size(); i++) {
//			tmp.add(first.get(i));
//			if(i%90==0){
//				System.out.println(tmp.get(0));
//				sendInform(tmp, "3962335");
//				tmp = new ArrayList<>();
//			}
//		}
//		System.out.println(tmp.get(tmp.size()-1));
//		sendInform(tmp, "3962335");
		
//		//第三期
//		String pathFirst = "F:\\send\\third.txt";
//		List<String> first = send(pathFirst);
//		List<String> tmp = new ArrayList<>();
//		
//		for(int i = 0; i < first.size(); i++) {
//			tmp.add(first.get(i));
//			if(i%90==0){
//				System.out.println(tmp.get(0));
//				sendInform(tmp, "3962336");
//				tmp = new ArrayList<>();
//			}
//		}
//		System.out.println(tmp.get(tmp.size()-1));
//		sendInform(tmp, "3962336");
		
//		//第四A期
//		String pathFirst = "F:\\send\\forthA.txt";
//		List<String> first = send(pathFirst);
//		List<String> tmp = new ArrayList<>();
//		
//		for(int i = 0; i < first.size(); i++) {
//			tmp.add(first.get(i));
//			if(i%90==0){
//				System.out.println(tmp.get(0));
//				sendInform(tmp, "3952345");
//				tmp = new ArrayList<>();
//			}
//		}
//		System.out.println(tmp.get(tmp.size()-1));
//		sendInform(tmp, "3952345");
		
		//第四B期
//		String pathFirst = "F:\\send\\forthB.txt";
//		List<String> first = send(pathFirst);
		List<String> first = new ArrayList<>();
		first.add("15011531008");
		first.add("17688547008");
		List<String> tmp = new ArrayList<>();
		
		for(int i = 0; i < first.size(); i++) {
			tmp.add(first.get(i));
			if(i%90==0){
				System.out.println(tmp.get(0));
				sendInform(tmp, "3952346");
				tmp = new ArrayList<>();
			}
		}
		System.out.println(tmp.get(tmp.size()-1));
		sendInform(tmp, "3952346");
	}

	private static List<String> setToList(Set<String> set) {
		List<String> reStrings = new ArrayList<>();
		if (set != null && set.size() > 0) {
			for (String string : set) {
				reStrings.add(string);
			}
		}
		return reStrings;
	}

	private static List<String> send(String path) {
		Set<String> numbers = new HashSet<>();
		 
		try {
			File filename = new File(path);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null) {
				line = br.readLine();
				if (!StringUtils.isEmpty(line) && line.length() == 11) {
					numbers.add(line);
				}
			}
			br.close();
			
			return setToList(numbers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setToList(numbers);
	}


	private String sendCode(String mobile) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(SERVER_URL);
		String nonce = "123456";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, nonce, curTime);
		// 设置请求的header
		httpPost.addHeader("AppKey", APP_KEY);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的的参数，requestBody参数
		List<NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("codeLen", CODELEN));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		/*
		 * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500 2.具体的code有问题的可以参考官网的Code状态表
		 */
		String res = EntityUtils.toString(response.getEntity(), "utf-8");
		ResponseJson responseJson = JSON.parseObject(res, ResponseJson.class);

		if (responseJson.getCode().equals("200")) {
			return responseJson.getObj();
		} else {
			return "";
		}
	}

	private static String sendInform(List<String> mobiles, String tempId) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(SERVER_URL_INFORM);
		String nonce = "123456";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, nonce, curTime);
		// 设置请求的header
		httpPost.addHeader("AppKey", APP_KEY);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的的参数，requestBody参数
		List<NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair("templateid", tempId));
		nvps.add(new BasicNameValuePair("mobiles", JSONArray.toJSONString(mobiles)));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		/*
		 * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500 2.具体的code有问题的可以参考官网的Code状态表
		 */
		String res = EntityUtils.toString(response.getEntity(), "utf-8");
		ResponseJson responseJson = JSON.parseObject(res, ResponseJson.class);

		if (responseJson.getCode().equals("200")) {
			System.out.println("successfully!");
			return responseJson.getObj();
		} else {
			System.out.println("failed!");
			return "";
		}
	}

}
