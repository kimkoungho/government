package com.kakaopay.jpa.repository;

import com.kakaopay.jpa.entity.RegionSupportEntity;
import com.kakaopay.region.model.inner.RegionSupportCsv;
import com.kakaopay.region.service.save.RegionSaveService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml"
})
@Transactional // rollback for
public class RegionSupportRepositoryTest {

    @Autowired
    private RegionSupportRepository regionSupportRepository;

    @Autowired
    private RegionSaveService regionSaveService;

    @Test
    public void findAll(){
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

        List<RegionSupportEntity> regionSupportEntityList = regionSupportRepository.findAll();

        System.out.println(regionSupportCsvList);

        Assert.assertEquals(2, regionSupportEntityList.size());
    }

    @Test
    public void getRegionNameListBySorted_지원금액(){
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
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(3L);
                        setRegionName("경기도");
                        setLimitDesc("1억원 이내");
                        setRateDesc("1%");
                    }
                });
            }
        };

        regionSaveService.saveAll(regionSupportCsvList);

        int k = 5;
        List<String> regionNameList = regionSupportRepository.getRegionNameListBySorted(k);
        // 거재시,경기도,강릉시
        for(String regionName : regionNameList){
            System.out.println(regionName);
        }

        Assert.assertEquals(3L, regionNameList.size());
    }

    @Test
    public void getRegionNameListBySorted_이차보전(){
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
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(3L);
                        setRegionName("경기도");
                        setLimitDesc("3억원 이내");
                        setRateDesc("1%");
                    }
                });
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(4L);
                        setRegionName("광주광역시");
                        setLimitDesc("3억원 이내");
                        setRateDesc("대출이자 전액");
                    }
                });
            }
        };

        regionSaveService.saveAll(regionSupportCsvList);

        int k = 3;
        List<String> regionNameList = regionSupportRepository.getRegionNameListBySorted(k);
        // 거재시,경기도,광주광역시
        for(String regionName : regionNameList){
            System.out.println(regionName);
        }

        Assert.assertEquals(3, regionNameList.size());
    }

    @Test
    public void getMinAvgRate(){
        List<RegionSupportCsv> regionSupportCsvList = new ArrayList<RegionSupportCsv>(){
            {
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(1L);
                        setRegionName("강릉시");
                        setLimitDesc("추천금액 이내");
                        setRateDesc("3%"); // 3
                    }
                });
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(2L);
                        setRegionName("거제시");
                        setLimitDesc("3억원 이내");
                        setRateDesc("2.5%~5.0%"); // 3.7
                    }
                });
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(3L);
                        setRegionName("경기도");
                        setLimitDesc("3억원 이내");
                        setRateDesc("1%"); // 1
                    }
                });
                add(new RegionSupportCsv(){
                    {
                        setRecordSeq(4L);
                        setRegionName("광주광역시");
                        setLimitDesc("3억원 이내");
                        setRateDesc("대출이자 전액"); // 100
                    }
                });
            }
        };

        regionSaveService.saveAll(regionSupportCsvList);

        String regionName = regionSupportRepository.getMinAvgRate();

        System.out.println(regionName);

        Assert.assertEquals("경기도", regionName);
    }
}