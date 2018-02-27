package com.lachesis.windranger.controller.register;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lachesis.windranger.authentication.dbmodel.RegisterInfo;
import com.lachesis.windranger.authentication.service.IRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
@Api(value = "/register", tags = {"register-register"}, description = "注册管理")
public class RegisterController {
	@Autowired
	IRegisterService iRegisterService;

	@RequestMapping(value = {"/signin"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "注册", httpMethod = "POST", response = Boolean.class)
	public boolean register(@ApiParam(required = false,
					value = "注册信息") @RequestBody(required = false) RegisterInfo registerInfo) {
		return iRegisterService.insertorUpdateRegisterInfo(registerInfo);
	}

	@RequestMapping(value = {"/signin/{registerId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取注册信息", httpMethod = "GET")
	public List<RegisterInfo> register(@ApiParam(required = false,
					value = "注册人id") @PathVariable(required = false) long registerId) {

		return iRegisterService.getRegisterInfo(registerId);
	}



}
