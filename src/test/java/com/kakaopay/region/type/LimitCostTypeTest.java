package com.kakaopay.region.type;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LimitCostTypeTest {

    @Test(expected = NumberFormatException.class)
    public void getCost_숫자파싱_오류() {
        String limitDesc = "a10억원 이내";

        LimitCostType limitCostType = LimitCostType.findBy(limitDesc);
        Long cost = limitCostType.getCost(limitDesc);
    }

    @Test
    public void getCost_none() {
        String limitDesc = "10억달러 이내";

        LimitCostType limitCostType = LimitCostType.findBy(limitDesc);
        Long cost = limitCostType.getCost(limitDesc);

        System.out.println(limitCostType.name() + " : " + cost);

        Assert.assertEquals(LimitCostType.NONE, limitCostType);
        Assert.assertEquals(0, cost.longValue());
    }

    @Test
    public void getCost_10억() {
        String limitDesc = "10억원 이내";

        LimitCostType limitCostType = LimitCostType.findBy(limitDesc);
        Long cost = limitCostType.getCost(limitDesc);

        System.out.println(limitCostType.name() + " : " + cost);

        Assert.assertEquals(LimitCostType.HUNDRED_MILLION, limitCostType);
        long expectCost = LimitCostType.HUNDRED_MILLION.getNumberUnit() * 10;
        Assert.assertEquals(expectCost, cost.longValue());
    }

    @Test
    public void getCost_3천만() {
        String limitDesc = "3 천만원 이내";

        LimitCostType limitCostType = LimitCostType.findBy(limitDesc);
        Long cost = limitCostType.getCost(limitDesc);

        System.out.println(limitCostType.name() + " : " + cost);

        Assert.assertEquals(LimitCostType.TEN_MILLION, limitCostType);
        long expectCost = LimitCostType.TEN_MILLION.getNumberUnit() * 3;
        Assert.assertEquals(expectCost, cost.longValue());
    }

    @Test
    public void getCost_5백만() {
        String limitDesc = "5백만원 이내";

        LimitCostType limitCostType = LimitCostType.findBy(limitDesc);
        Long cost = limitCostType.getCost(limitDesc);

        System.out.println(limitCostType.name() + " : " + cost);

        Assert.assertEquals(LimitCostType.MILLION, limitCostType);
        long expectCost = LimitCostType.MILLION.getNumberUnit() * 5;
        Assert.assertEquals(expectCost, cost.longValue());
    }
}