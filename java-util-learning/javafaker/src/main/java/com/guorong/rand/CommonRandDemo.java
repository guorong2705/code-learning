package com.guorong.rand;

import com.apifan.common.random.entity.Area;
import com.apifan.common.random.source.AreaSource;

public class CommonRandDemo {
    public static void main(String[] args) {
        Area area = AreaSource.getInstance().nextArea();
        System.out.println(area.getProvince() + "-" + area.getCity() + "-" + area.getCounty());
    }
}
