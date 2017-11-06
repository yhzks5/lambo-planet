package com.lambo.ucenter.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.ucenter.dao.mapper.UcenterUserMapper;
import com.lambo.ucenter.dao.model.UcenterUser;
import com.lambo.ucenter.dao.model.UcenterUserExample;

/**
* 降级实现UcenterUserService接口
* Created by lambo on 2017/4/27.
*/
public class UcenterUserServiceMock extends BaseServiceMock<UcenterUserMapper, UcenterUser, UcenterUserExample> implements UcenterUserService {

}
