package com.kakaopay.region.service.save;

import com.kakaopay.jpa.entity.RegionSupportEntity;
import com.kakaopay.jpa.repository.RegionRepository;
import com.kakaopay.jpa.repository.RegionSupportRepository;
import com.kakaopay.region.model.inner.RegionSupportCsv;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml"
})
public class RegionSaveServiceTest {

    @Autowired
    private RegionSaveService regionSaveService;

    @Autowired
    private RegionSupportRepository regionSupportRepository;

    //TODO
    // error 는 테이블 생성전에 alter 를 시도하기 떄문
    @Test
    public void saveAll() {
        List<RegionSupportCsv> regionSupportCsvList = new ArrayList<RegionSupportCsv>(){
            {
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(1L);
                        setRegionName("강릉시");
                        setTarget("강릉시 소재 중소기업으로서 강릉시장이 추천한 자");
                        setUsage("운전");
                        setLimitDesc("추천금액 이내");
                        setRateDesc("3%");
                        setInstitute("강릉시");
                        setManagement("강릉지점");
                        setReception("강릉시 소재 영업점");
                    }
                });
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(2L);
                        setRegionName("강원도");
                        setTarget("강원도 소재 중소기업으로서 강원도지사가 추천한 자");
                        setUsage("운전");
                        setLimitDesc("8억원 이내");
                        setRateDesc("3%~5%");
                        setInstitute("강원도");
                        setManagement("춘천지점");
                        setReception("강원도 소재 영업점");
                    }
                });
            }
        };

        int count = regionSaveService.saveAll(regionSupportCsvList);

        System.out.println(count);

        Assert.assertEquals(2, count);

        List<RegionSupportEntity> regionSupportEntityList = regionSupportRepository.findAll();
        for(RegionSupportEntity regionSupportEntity : regionSupportEntityList){
            System.out.println(regionSupportEntity);
        }
    }
}