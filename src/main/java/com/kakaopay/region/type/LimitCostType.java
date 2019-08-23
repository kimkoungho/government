package com.kakaopay.region.type;

import org.apache.commons.lang3.math.NumberUtils;


public enum LimitCostType {
    HUNDRED_MILLION("억원",  100000000),
    TEN_MILLION(    "천만원",  10000000),
    MILLION(        "백만원",   1000000),
    NONE(               "",         0);

    private String koreaUnit;
    private Integer numberUnit;

    LimitCostType(String koreaUnit, Integer numberUnit){
        this.koreaUnit = koreaUnit;
        this.numberUnit = numberUnit;
    }

    public String getKoreaUnit() {
        return koreaUnit;
    }

    public Integer getNumberUnit() {
        return numberUnit;
    }

    public Long getCost(String limitDesc){
        if(LimitCostType.NONE == this){
            return 0L;
        }

        String cost = limitDesc.substring(0, limitDesc.indexOf(this.getKoreaUnit())).trim();
        return NumberUtils.createLong(cost) * this.getNumberUnit();
    }

    /** 지원한도 문자열에서 단위 찾기 */
    public static LimitCostType findBy(String limitDesc){
        for(LimitCostType limitCostType : values()){
            if(limitDesc.contains(limitCostType.getKoreaUnit())){
                return limitCostType;
            }
        }
        return LimitCostType.NONE;
    }
}
