package com.kakaopay.jpa.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "region_support")
public class RegionSupportEntity implements Serializable {

    private static final long serialVersionUID = -8683021251481328222L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_support_seq", nullable = false)
    private Long regionSupportSeq;

    @Column(name = "record_seq", nullable = false)
    private Long recordSeq;

    @Column(name = "target")
    private String target;

    @Column(name = "usage")
    private String usage;

    @Column(name = "limit_desc")
    private String limitDesc; // 지원한도 문자열 (csv 파일 입력)

    @Column(name = "limit_cost")
    private Long limitCost; // 최대 비용

    @Column(name = "rate_desc")
    private String rateDesc; // 이차보전 문자열 (csv 파일 입력)

    @Column(name = "avg_rate")
    private Double avgRate; // 평균 이차보전

    @Column(name = "institute")
    private String institute;

    @Column(name = "management")
    private String management;

    @Column(name = "reception")
    private String reception;

    @Column(name = "create_dt", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updateDate;

    @OneToOne
    private RegionEntity regionEntity;

    public Long getRegionSupportSeq() {
        return regionSupportSeq;
    }

    public void setRegionSupportSeq(Long regionSupportSeq) {
        this.regionSupportSeq = regionSupportSeq;
    }

    public Long getRecordSeq() {
        return recordSeq;
    }

    public void setRecordSeq(Long recordSeq) {
        this.recordSeq = recordSeq;
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
    }

    public Long getLimitCost() {
        return limitCost;
    }

    public void setLimitCost(Long limitCost) {
        this.limitCost = limitCost;
    }

    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    public Double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Double avgRate) {
        this.avgRate = avgRate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public RegionEntity getRegionEntity() {
        return regionEntity;
    }

    public void setRegionEntity(RegionEntity regionEntity) {
        this.regionEntity = regionEntity;
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
