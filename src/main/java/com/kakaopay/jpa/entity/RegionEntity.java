package com.kakaopay.jpa.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "region")
public class RegionEntity implements Serializable {

    private static final long serialVersionUID = -9001198124094210159L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_code_generator")
    @GenericGenerator(name = "region_code_generator", strategy = "com.kakaopay.jpa.util.RegionCodeGenerator")
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 40, unique = true)
    private String name;

    @OneToOne(mappedBy = "regionEntity", cascade = CascadeType.ALL)
    private RegionSupportEntity regionSupportEntity;

    public RegionEntity() { }
    public RegionEntity(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public RegionSupportEntity getRegionSupportEntity() {
        return regionSupportEntity;
    }

    public String getName() {
        return name;
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
