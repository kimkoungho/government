package com.kakaopay.region.service.save;

import com.kakaopay.common.util.ModelConverter;
import com.kakaopay.jpa.entity.RegionEntity;
import com.kakaopay.jpa.entity.RegionSupportEntity;
import com.kakaopay.jpa.repository.RegionRepository;
import com.kakaopay.jpa.repository.RegionSupportRepository;
import com.kakaopay.region.model.inner.RegionSupportCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegionSaveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionSaveService.class);

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionSupportRepository regionSupportRepository;

    @Autowired
    private ModelConverter modelConverter;

    /** csv 모델 리스트로 데이터 저장 */
    public int saveAll(List<RegionSupportCsv> regionSupportCsvList){
        LOGGER.debug("RegionSaveService.saveAll ... regionSupportCsvList: {}", regionSupportCsvList);
        /**************** region 저장 ****************/
        // DB 에 없는 region 추출
        List<RegionEntity> saveRegionEntityList = regionSupportCsvList.stream()
                .map(regionSupportCsv -> new RegionEntity(regionSupportCsv.getRegionName()))
                .collect(Collectors.toList());
        List<RegionEntity> regionEntityList = regionRepository.saveAll(saveRegionEntityList);
        // region 이름 unique
        Map<String, RegionEntity> regionEntityMap = regionEntityList.stream()
                .collect(Collectors.toMap(RegionEntity::getName, Function.identity()));

        /************** region support 저장 ****************/
        LocalDateTime now = LocalDateTime.now();
        List<RegionSupportEntity> regionSupportEntityList = regionSupportCsvList.stream()
                .map(regionSupportCsv -> {
                    RegionSupportEntity regionSupportEntity = modelConverter.map(regionSupportCsv, RegionSupportEntity.class);
                    regionSupportEntity.setRegionEntity(regionEntityMap.get(regionSupportCsv.getRegionName()));
                    regionSupportEntity.setCreateDate(now);
                    regionSupportEntity.setUpdateDate(now);
                    return regionSupportEntity;
                }).collect(Collectors.toList());

        return regionSupportRepository.saveAll(regionSupportEntityList).size();
    }
}
