package com.lambo.ucenter.rpc.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ucenter.dao.mapper.UcenterOauthMapper;
import com.lambo.ucenter.dao.model.UcenterOauth;
import com.lambo.ucenter.dao.model.UcenterOauthExample;
import com.lambo.ucenter.rpc.api.UcenterOauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterOauthService实现
* Created by lambo on 2017/4/27.
*/
@Service
@Transactional
@BaseService
public class UcenterOauthServiceImpl extends BaseServiceImpl<UcenterOauthMapper, UcenterOauth, UcenterOauthExample> implements UcenterOauthService {

    private static Logger _log = LoggerFactory.getLogger(UcenterOauthServiceImpl.class);

    @Autowired
    UcenterOauthMapper ucenterOauthMapper;

}