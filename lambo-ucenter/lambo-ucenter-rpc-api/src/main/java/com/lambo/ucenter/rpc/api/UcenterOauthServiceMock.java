package com.lambo.ucenter.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.ucenter.dao.mapper.UcenterOauthMapper;
import com.lambo.ucenter.dao.model.UcenterOauth;
import com.lambo.ucenter.dao.model.UcenterOauthExample;

/**
* 降级实现UcenterOauthService接口
* Created by lambo on 2017/4/27.
*/
public class UcenterOauthServiceMock extends BaseServiceMock<UcenterOauthMapper, UcenterOauth, UcenterOauthExample> implements UcenterOauthService {

}
