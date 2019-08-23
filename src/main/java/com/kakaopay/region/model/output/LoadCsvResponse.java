package com.kakaopay.region.model.output;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoadCsvResponse {
    /** 첨부파일 이름 */
    private String fileName;

    /** 저장된 협약지원 개수 */
    private int savedCount;

    public LoadCsvResponse(String fileName, int savedCount){
        this.fileName = fileName;
        this.savedCount = savedCount;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSavedCount() {
        return savedCount;
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
