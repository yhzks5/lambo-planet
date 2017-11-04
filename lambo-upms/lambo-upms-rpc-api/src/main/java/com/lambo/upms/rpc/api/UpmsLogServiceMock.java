package com.lambo.upms.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.upms.dao.mapper.UpmsLogMapper;
import com.lambo.upms.dao.model.UpmsLog;
import com.lambo.upms.dao.model.UpmsLogExample;

/**
* 降级实现UpmsLogService接口
* Created by shulambo on 2017/3/20.
*/
public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

}
