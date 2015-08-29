package com.android.tonight8.io.live.paramentity;

import com.android.tonight8.dao.entity.Page;
import com.android.tonight8.dao.entity.Regional;
import com.android.tonight8.io.BaseParamEntity;

import junit.framework.Test;

/**
 * Created by LiXiaoSong
 * Date: 2015/8/26 0026
 */
public class TestParamEntity extends BaseParamEntity {
    private Regional regional;
    private boolean isToday;
    private Page page;

    public TestParamEntity() {
        regional = new Regional();
        page = new Page();
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setIsToday(boolean isToday) {
        this.isToday = isToday;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "TestParamEntity{" +
                "regional=" + regional +
                ", isToday=" + isToday +
                ", page=" + page +
                '}';
    }
}
