package com.lambo.upms.rpc.api;

import com.lambo.common.base.BaseService;
import com.lambo.upms.dao.model.UpmsUserRole;
import com.lambo.upms.dao.model.UpmsUserRoleExample;

/**
* UpmsUserRoleService接口
* Created by shulambo on 2017/3/20.
*/
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}