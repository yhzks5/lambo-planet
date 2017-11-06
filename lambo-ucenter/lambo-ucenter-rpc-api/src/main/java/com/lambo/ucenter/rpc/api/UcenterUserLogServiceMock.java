package com.lambo.ucenter.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.ucenter.dao.mapper.UcenterUserLogMapper;
import com.lambo.ucenter.dao.model.UcenterUserLog;
import com.lambo.ucenter.dao.model.UcenterUserLogExample;

/**
* 降级实现UcenterUserLogService接口
* Created by lambo on 2017/4/27.
*/
public class UcenterUserLogServiceMock extends BaseServiceMock<UcenterUserLogMapper, UcenterUserLog, UcenterUserLogExample> implements UcenterUserLogService {

}
