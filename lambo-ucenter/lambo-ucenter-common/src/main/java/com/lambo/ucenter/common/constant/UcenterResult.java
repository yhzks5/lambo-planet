package com.lambo.ucenter.common.constant;

import com.lambo.common.base.BaseResult;

/**
 * ucenter系统常量枚举类
 * Created by lambo on 2017/4/26.
 */
public class UcenterResult extends BaseResult {

    public UcenterResult(UcenterResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
