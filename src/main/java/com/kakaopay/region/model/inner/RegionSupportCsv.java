package com.kakaopay.region.model.inner;

import com.kakaopay.region.type.LimitCostType;
import com.kakaopay.region.type.RateType;
import com.opencsv.bean.CsvBindByPosition;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


public class RegionSupportCsv implements Serializable {

    private static final long serialVersionUID = -4470598444141048603L;
    /** 파일상 레코드 시퀀스 */
    @CsvBindByPosition(position = 0)
    private Long recordSeq;

    /** 기관이름 */
    @CsvBindByPosition(position = 1)
    private String regionName;

    /** 지원대상 */
    @CsvBindByPosition(position = 2)
    private String target;

    /** 용도 */
    @CsvBindByPosition(position = 3)
    private String usage;

    /** 지원한도 */
    @CsvBindByPosition(position = 4)
    private String limitDesc; // 지원한도 문자열 (csv 파일 입력)
    private Long limitCost; // 최대 비용

    /** 이차보전 */
    @CsvBindByPosition(position = 5)
    private String rateDesc; // 이차보전 문자열 (csv 파일 입력)
    private Double avgRate; // 평균 이차보전

    /** 추천기관 */
    @CsvBindByPosition(position = 6)
    private String institute;

    /** 관리점 */
    @CsvBindByPosition(position = 7)
    private String management;

    /** 취급점 */
    @CsvBindByPosition(position = 8)
    private String reception;

    public Long getRecordSeq() {
        return recordSeq;
    }

    public void setRecordSeq(Long recordSeq) {
        this.recordSeq = recordSeq;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getLimitDesc() {
        return limitDesc;
    }

    public void setLimitDesc(String limitDesc) {
        this.limitDesc = limitDesc;
        // 지원한도 계산
        LimitCostType limitCostType = LimitCostType.findBy(limitDesc);
        this.limitCost = limitCostType.getCost(limitDesc);
    }

    public Long getLimitCost() {
        return limitCost;
    }

    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;

        RateType rateType = RateType.findBy(rateDesc);
        this.avgRate = rateType.getAvgRate(rateDesc);
    }

    public Double getAvgRate() {
        return avgRate;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getReception() {
        return reception;
    }

    public void setReception(String reception) {
        this.reception = reception;
    }

    @Override
    public boolean equals(Object o){
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
