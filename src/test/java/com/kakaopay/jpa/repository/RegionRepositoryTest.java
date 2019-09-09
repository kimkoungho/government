package com.kakaopay.jpa.repository;


import com.kakaopay.jpa.entity.RegionEntity;
import com.kakaopay.region.model.inner.RegionSupportCsv;
import com.kakaopay.region.service.save.RegionSaveService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml"
})
@Transactional
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionSaveService regionSaveService;

    @Before
    public void setUp(){
        List<RegionSupportCsv> regionSupportCsvList = new ArrayList<RegionSupportCsv>(){
            {
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(1L);
                        setRegionName("강릉시");
                        setLimitDesc("추천금액 이내");
                        setRateDesc("3%");
                    }
                });
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(2L);
                        setRegionName("거제시");
                        setLimitDesc("3억원 이내");
                        setRateDesc("2.5%~5.0%");
                    }
                });
            }
        };
        regionSaveService.saveAll(regionSupportCsvList);
    }

    @Test
    public void findAll(){

        List<RegionEntity> regionEntityList = regionRepository.findAll();

        for(RegionEntity regionEntity : regionEntityList){
            System.out.println(regionEntity);
        }
    }

    @Test
    public void findByName(){

        final String regionName = "거제시";

        Optional<RegionEntity> regionEntity = regionRepository.findByName(regionName);
        System.out.println(regionEntity.get());

        Assert.assertTrue(regionEntity.isPresent());
        Assert.assertEquals(regionName, regionEntity.get().getName());
    }
}