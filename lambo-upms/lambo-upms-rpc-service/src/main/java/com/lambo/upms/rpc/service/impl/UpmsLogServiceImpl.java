package com.lambo.upms.rpc.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.upms.dao.mapper.UpmsLogMapper;
import com.lambo.upms.dao.model.UpmsLog;
import com.lambo.upms.dao.model.UpmsLogExample;
import com.lambo.upms.rpc.api.UpmsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsLogService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

    private static Logger _log = LoggerFactory.getLogger(UpmsLogServiceImpl.class);

    @Autowired
    UpmsLogMapper upmsLogMapper;

}