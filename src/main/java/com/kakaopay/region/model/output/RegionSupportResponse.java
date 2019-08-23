package com.kakaopay.region.model.output;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kakaopay.jpa.entity.RegionEntity;
import com.kakaopay.jpa.entity.RegionSupportEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


import java.time.LocalDateTime;

public class RegionSupportResponse {
    /** 협약 지원 시퀀스 */
    private Long regionSupportSeq;

    /** 파일상 레코드 시퀀스 */
    private Long recordSeq;

    /** 기관코드 */
    private String regionCode;
    /** 기관이름 */
    private String regionName;

    /** 지원대상 */
    private String target;

    /** 용도 */
    private String usage;

    /** 지원한도 */
    private String limitDesc;

    /** 이차보전 */
    private String rateDesc;

    /** 추천기관 */
    private String institute;

    /** 관리점 */
    private String management;

    /** 취급점 */
    private String reception;

    /** 생성일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime createDate;

    /** 수정일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime updateDate;


    public void setRegionEntity(RegionEntity regionEntity){
        this.regionCode = regionEntity.getCode();
        this.regionName = regionEntity.getName();
    }

    public void setRegionSupportEntity(RegionSupportEntity regionSupportEntity){
        this.regionSupportSeq = regionSupportEntity.getRegionSupportSeq();
        this.recordSeq = regionSupportEntity.getRecordSeq();
        this.target = regionSupportEntity.getTarget();
        this.usage = regionSupportEntity.getUsage();
        this.limitDesc = regionSupportEntity.getLimitDesc();
        this.rateDesc = regionSupportEntity.getRateDesc();
        this.institute = regionSupportEntity.getInstitute();
        this.management = regionSupportEntity.getManagement();
        this.reception = regionSupportEntity.getReception();
        this.createDate = regionSupportEntity.getCreateDate();
        this.updateDate = regionSupportEntity.getUpdateDate();
    }

    public Long getRegionSupportSeq() {
        return regionSupportSeq;
    }

    public Long getRecordSeq() {
        return recordSeq;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getTarget() {
        return target;
    }

    public String getUsage() {
        return usage;
    }

    public String getLimitDesc() {
        return limitDesc;
    }

    public String getRateDesc() {
        return rateDesc;
    }

    public String getInstitute() {
        return institute;
    }

    public String getManagement() {
        return management;
    }

    public String getReception() {
        return reception;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
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
