package com.lambo.upms.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.upms.dao.mapper.UpmsSystemMapper;
import com.lambo.upms.dao.model.UpmsSystem;
import com.lambo.upms.dao.model.UpmsSystemExample;

/**
* 降级实现UpmsSystemService接口
* Created by shulambo on 2017/3/20.
*/
public class UpmsSystemServiceMock extends BaseServiceMock<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    @Override
    public UpmsSystem selectUpmsSystemByName(String name) {
        return null;
    }

}
