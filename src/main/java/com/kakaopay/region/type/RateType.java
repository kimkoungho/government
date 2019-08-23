package com.kakaopay.region.type;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.regex.Pattern;


public enum RateType {
    ALL{
        @Override
        public Double getAvgRate(String rateDesc) {
            return 100.0;
        }
    },
    SINGLE{
        @Override
        public Double getAvgRate(String rateDesc) {
            return NumberUtils.createDouble(rateDesc.replace("%", "").trim());
        }
    },
    RANGE{
        @Override
        public Double getAvgRate(String rateDesc) {
            String[] rates = rateDesc.split("~");
            Double sum =  Arrays.stream(rates)
                    .map(rateStr -> {
                        return NumberUtils.createDouble(rateStr.replace("%", "").trim());
                    }).reduce(0.0, Double::sum);
            return sum / rates.length;
        }
    },
    NONE{
        @Override
        public Double getAvgRate(String rateDesc) {
            return 0.0;
        }
    };

    private static Pattern SINGLE_PATTERN = Pattern.compile("((\\d)+((\\.)(\\d))*[%])");
    private static Pattern RANGE_PATTERN = Pattern.compile("(([\\s]*~[\\s]*)*((\\d)+((\\.)(\\d))*[%]))+");

    public abstract Double getAvgRate(String rateDesc);

    public static RateType findBy(String rateDesc){
        if(rateDesc.contains("전액")){
            return RateType.ALL;
        }else if(SINGLE_PATTERN.matcher(rateDesc).matches()){
            return RateType.SINGLE;
        }else if(RANGE_PATTERN.matcher(rateDesc).matches()){
            return RateType.RANGE;
        }else {
            return RateType.NONE;
        }
    }
}
