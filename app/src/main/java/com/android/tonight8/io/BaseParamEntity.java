package com.android.tonight8.io;

import com.android.tonight8.dao.entity.Common;

/**
 * 所有参数实体都需要继承该实体
 * Created by LiXiaoSong
 * Date: 2015/8/26 0026
 */
public class BaseParamEntity {
    private Common common;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }
}
