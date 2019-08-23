package com.kakaopay.region.service;


import com.kakaopay.common.exceptioin.ApiException;
import com.kakaopay.common.exceptioin.ApiExceptionCode;
import com.kakaopay.jpa.entity.RegionEntity;
import com.kakaopay.jpa.entity.RegionSupportEntity;
import com.kakaopay.jpa.repository.RegionRepository;
import com.kakaopay.jpa.repository.RegionSupportRepository;
import com.kakaopay.region.model.inner.RegionSupportCsv;
import com.kakaopay.region.model.input.RegionSupportRequest;
import com.kakaopay.region.model.output.LoadCsvResponse;
import com.kakaopay.region.model.output.RegionSupportResponse;
import com.kakaopay.region.service.save.RegionSaveService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionService.class);

    @Autowired
    private RegionSupportRepository regionSupportRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionSaveService regionSaveService;

    /** csv 파일을 DB 에 저장 */
    public LoadCsvResponse loadFileAndSaveAll(MultipartFile multipartFile) {
        LOGGER.debug("RegionService.loadFileAndSaveAll");

        try {
            Reader reader = new InputStreamReader(multipartFile.getInputStream(), "EUC-KR");
            CsvToBean<RegionSupportCsv> csvToBean = new CsvToBeanBuilder<RegionSupportCsv>(reader)
                    .withType(RegionSupportCsv.class)
                    .withSkipLines(1) // header 무시
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<RegionSupportCsv> regionSupportCsvList = csvToBean.parse();
            int count = regionSaveService.saveAll(regionSupportCsvList);

            return new LoadCsvResponse(multipartFile.getOriginalFilename(), count);
        }catch (IOException io){
            LOGGER.error("CSV FILE IOException .. ", io);
            // 예외관리 TODO
            throw new RuntimeException();
        }
    }

    /** 지자체 전체 목록 조회 */
    public List<RegionSupportResponse> getRegionSupportList(){
        LOGGER.debug("RegionService.getRegionSupportList");
        List<RegionEntity> regionEntityList = regionRepository.findAll();
        return regionEntityList.stream()
                .map(regionEntity -> {
                    RegionSupportResponse regionSupportResponse = new RegionSupportResponse();
                    regionSupportResponse.setRegionEntity(regionEntity);
                    regionSupportResponse.setRegionSupportEntity(regionEntity.getRegionSupportEntity());
                    return regionSupportResponse;
                }).collect(Collectors.toList());
    }

    /** 지자체 1개 조회 */
    public RegionSupportResponse getRegionSupportResponse(String regionName){
        LOGGER.debug("RegionService.getRegionSupportResponse ... regionName: {}", regionName);

        Optional<RegionEntity> regionEntityOptional = regionRepository.findByName(regionName);
        if(regionEntityOptional.isPresent()){
            RegionSupportResponse regionSupportResponse = new RegionSupportResponse();
            regionSupportResponse.setRegionEntity(regionEntityOptional.get());
            regionSupportResponse.setRegionSupportEntity(regionEntityOptional.get().getRegionSupportEntity());
            return regionSupportResponse;
        }

        return new RegionSupportResponse();
    }

    /** 지자체 정보 수정후 (수정된 정보 반환) */
    public RegionSupportResponse updateRegionSupport(String regionName, RegionSupportRequest regionSupportRequest){
        LOGGER.debug("RegionService.updateRegionSupport ... regionName: {}, regionSupportRequest: {}", regionName, regionSupportRequest);

        Optional<RegionEntity> regionEntityOptional = regionRepository.findByName(regionName);
        if(!regionEntityOptional.isPresent()){
            throw new ApiException(ApiExceptionCode.NOT_FOUND_UPDATE_TARGET, RegionEntity.class.getName(), regionName);
        }

        RegionSupportEntity regionSupportEntity = regionEntityOptional.get().getRegionSupportEntity();
        if(StringUtils.isNotEmpty(regionSupportRequest.getTarget())){
            regionSupportEntity.setTarget(regionSupportRequest.getTarget());
        }
        if(StringUtils.isNotEmpty(regionSupportRequest.getUsage())){
            regionSupportEntity.setUsage(regionSupportRequest.getUsage());
        }
        if(StringUtils.isNotEmpty(regionSupportRequest.getLimitDesc())){
            regionSupportEntity.setLimitDesc(regionSupportRequest.getLimitDesc());
        }
        if(StringUtils.isNotEmpty(regionSupportRequest.getRateDesc())){
            regionSupportEntity.setRateDesc(regionSupportRequest.getRateDesc());
        }
        if(StringUtils.isNotEmpty(regionSupportRequest.getInstitute())){
            regionSupportEntity.setInstitute(regionSupportRequest.getInstitute());
        }
        if(StringUtils.isNotEmpty(regionSupportRequest.getManagement())){
            regionSupportEntity.setManagement(regionSupportRequest.getManagement());
        }
        if(StringUtils.isNotEmpty(regionSupportRequest.getReception())){
            regionSupportEntity.setReception(regionSupportRequest.getReception());
        }

        regionSupportEntity.setUpdateDate(LocalDateTime.now());
        regionSupportEntity = regionSupportRepository.saveAndFlush(regionSupportEntity);

        RegionSupportResponse regionSupportResponse = new RegionSupportResponse();
        regionSupportResponse.setRegionEntity(regionEntityOptional.get());
        regionSupportResponse.setRegionSupportEntity(regionSupportEntity);
        return regionSupportResponse;
    }

    /** 지원한도 컬럼에서 지원금액으로 내림차순 , 이차보전 평균 비율 오름차순 (k 개수만) */
    public List<String> getRegionNameListBySorted(Integer count){
        LOGGER.debug("RegionService.getRegionNameListBySorted ... count: {}", count);
        return regionSupportRepository.getRegionNameListBySorted(count);
    }

    /** 이차 보전 비율이 가장 작은것 */
    public String getMinAvgRate(){
        LOGGER.debug("RegionService.getMinAvgRate");
        return regionSupportRepository.getMinAvgRate();
    }

    /** TODO: 기사에서 검색 */

    // TODO: 회원가입, 로그인(인증 헤더토큰 만들기), 인증 (인터셉터)
}
