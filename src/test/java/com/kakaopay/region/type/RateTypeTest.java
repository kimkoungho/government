package com.kakaopay.region.type;

import org.junit.Assert;
import org.junit.Test;


public class RateTypeTest {

    @Test
    public void getAvgRate_전액() {
        String rateDesc = "대출이자 전액";

        RateType rateType = RateType.findBy(rateDesc);

        Double avgRate = rateType.getAvgRate(rateDesc);

        System.out.println(rateType.name() + " : " + avgRate);

        Assert.assertEquals(RateType.ALL, rateType);
        Assert.assertEquals(Double.doubleToLongBits(100.0), Double.doubleToLongBits(avgRate));
    }

    @Test
    public void getAvgRate_1개() {
        String rateDesc = "2.5%";

        RateType rateType = RateType.findBy(rateDesc);

        Double avgRate = rateType.getAvgRate(rateDesc);

        System.out.println(rateType.name() + " : " + avgRate);

        Assert.assertEquals(RateType.SINGLE, rateType);
        Assert.assertEquals(Double.doubleToLongBits(2.5), Double.doubleToLongBits(avgRate));
    }

    @Test
    public void getAvgRate_1개_숫자변환오류() {
        String rateDesc = "2.5a%";

        RateType rateType = RateType.findBy(rateDesc);

        Assert.assertEquals(RateType.NONE, rateType);
    }

    @Test
    public void getAvgRate_2개() {
        String rateDesc = "1.0%~ 2.0%";

        RateType rateType = RateType.findBy(rateDesc);

        Double avgRate = rateType.getAvgRate(rateDesc);

        System.out.println(rateType.name() + " : " + avgRate);

        Assert.assertEquals(RateType.RANGE, rateType);
        Assert.assertEquals(Double.doubleToLongBits(1.5), Double.doubleToLongBits(avgRate));
    }

    @Test
    public void getAvgRate_3개() {
        String rateDesc = "1.0%~2.0%~3.0%";

        RateType rateType = RateType.findBy(rateDesc);

        Double avgRate = rateType.getAvgRate(rateDesc);

        System.out.println(rateType.name() + " : " + avgRate);

        Assert.assertEquals(RateType.RANGE, rateType);
        Assert.assertEquals(Double.doubleToLongBits(2), Double.doubleToLongBits(avgRate));
    }
}