package com.lambo.upms.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.upms.dao.mapper.UpmsUserMapper;
import com.lambo.upms.dao.model.UpmsUser;
import com.lambo.upms.dao.model.UpmsUserExample;

/**
* 降级实现UpmsUserService接口
* Created by shulambo on 2017/3/20.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }

}
