package com.lambo.ucenter.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.ucenter.dao.mapper.UcenterUserDetailsMapper;
import com.lambo.ucenter.dao.model.UcenterUserDetails;
import com.lambo.ucenter.dao.model.UcenterUserDetailsExample;

/**
* 降级实现UcenterUserDetailsService接口
* Created by shulambo on 2017/4/27.
*/
public class UcenterUserDetailsServiceMock extends BaseServiceMock<UcenterUserDetailsMapper, UcenterUserDetails, UcenterUserDetailsExample> implements UcenterUserDetailsService {

}
