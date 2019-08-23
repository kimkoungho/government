package com.kakaopay.jpa.repository;



import com.kakaopay.jpa.entity.QRegionEntity;
import com.kakaopay.jpa.entity.QRegionSupportEntity;
import com.kakaopay.jpa.entity.RegionSupportEntity;

import java.util.List;

public class RegionSupportRepositoryImpl extends BaseQueryDslSupport implements RegionSupportRepositoryCustom {

    private QRegionSupportEntity regionSupportEntity = QRegionSupportEntity.regionSupportEntity;
    private QRegionEntity regionEntity = QRegionEntity.regionEntity;

    public RegionSupportRepositoryImpl() {
        super(RegionSupportEntity.class);
    }

    @Override
    public List<String> getRegionNameListBySorted(Integer count) {
        return jpaQueryFactory
                .select(regionEntity.name)
                .from(regionEntity)
                .innerJoin(regionEntity.regionSupportEntity, regionSupportEntity)
                .orderBy(regionSupportEntity.limitCost.desc(), regionSupportEntity.avgRate.asc())
                .limit(count)
                .fetch();
    }

    @Override
    public String getMinAvgRate() {
        return jpaQueryFactory
                .select(regionEntity.name)
                .from(regionEntity)
                .innerJoin(regionEntity.regionSupportEntity, regionSupportEntity)
                .orderBy(regionSupportEntity.avgRate.asc())
                .fetchFirst();
    }
}
