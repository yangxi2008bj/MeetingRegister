package com.lachesis.windranger.controller.authentication;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lachesis.windranger.authentication.service.IRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
@Api(value = "/login", tags = {"login-login"}, description = "登录管理")
public class LoginController {

	@Autowired
	IRegisterService iRegisterService;

	@RequestMapping(value = {"/mobile"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据手机登录", httpMethod = "GET", response = Boolean.class)
	public Map<String, Object> accountLogin(
					@ApiParam(required = false,
									value = "手机号码") @RequestParam(required = false) String mobile,
					@ApiParam(required = false, value = "验证码") @RequestParam(
									required = false) String verification) {
		return iRegisterService.accountLogin(mobile, verification);
	}

	@RequestMapping(value = {"/verificationCode"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "发送验证码", httpMethod = "GET")
	public void sendVerification(@ApiParam(required = false,
					value = "手机号码") @RequestParam(required = false) String mobile) {
		iRegisterService.sendVerification(mobile);
	}

}
