package com.lambo.upms.rpc.api;

import com.lambo.common.base.BaseServiceMock;
import com.lambo.upms.dao.mapper.UpmsOrganizationMapper;
import com.lambo.upms.dao.model.UpmsOrganization;
import com.lambo.upms.dao.model.UpmsOrganizationExample;

/**
* 降级实现UpmsOrganizationService接口
* Created by shulambo on 2017/3/20.
*/
public class UpmsOrganizationServiceMock extends BaseServiceMock<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

}
