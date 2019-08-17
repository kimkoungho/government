package com.kakaopay.jpa.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Table(name = "local_government")
public class LocalGovernmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "government_code", nullable = false)
    private String governmentCode;

    @Column(name = "government_name", nullable = false)
    private String governmentName;

    public void setGovernmentCode(String governmentCode){
        this.governmentCode = governmentCode;
    }
    public void setGovernmentName(String governmentName){
        this.governmentName = governmentName;
    }

    public String getGovernmentCode(){
        return this.governmentCode;
    }
    public String getGovernmentName(){
        return this.governmentName;
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
