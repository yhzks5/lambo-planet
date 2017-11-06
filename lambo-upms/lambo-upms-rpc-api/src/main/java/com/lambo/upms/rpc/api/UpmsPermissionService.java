package com.lambo.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.lambo.common.base.BaseService;
import com.lambo.upms.dao.model.UpmsPermission;
import com.lambo.upms.dao.model.UpmsPermissionExample;

/**
* UpmsPermissionService接口
* Created by lambo on 2017/3/20.
*/
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}