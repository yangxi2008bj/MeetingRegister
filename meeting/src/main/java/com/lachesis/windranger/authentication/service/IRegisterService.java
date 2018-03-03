package com.lachesis.windranger.authentication.service;

import java.util.List;
import java.util.Map;
import com.lachesis.windranger.authentication.dbmodel.Account;
import com.lachesis.windranger.authentication.dbmodel.RegisterInfo;

public interface IRegisterService {
	Account insertorUpdateAccount(Account account);

	boolean insertorUpdateRegisterInfo(RegisterInfo registerInfo);

	List<RegisterInfo> getRegisterInfo(long registerId);

	Map<String, Object> accountLogin(String mobile, String verification);

	void sendVerification(String mobile);
	
//	void sendInfo(String meeting);
}
