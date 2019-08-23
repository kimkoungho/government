package com.kakaopay;

import com.kakaopay.region.model.inner.RegionSupportCsv;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;


import java.io.*;
import java.util.List;

public class CsvParserTest {

    // TODO: 컬럼수 가 다를 때 ...



    @Test
    public void csvParse_Test() throws IOException {
        final String filePath = "/data/data.csv";
        File file = ResourceUtils.getFile(getClass().getResource(filePath));
        Reader reader = new InputStreamReader(new FileInputStream(file), "EUC-KR");
        CsvToBean<RegionSupportCsv> csvToBean = new CsvToBeanBuilder<RegionSupportCsv>(reader)
                .withType(RegionSupportCsv.class)
                .withSkipLines(1) // header 무시
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<RegionSupportCsv> regionSupportCsvList = csvToBean.parse();
        for(RegionSupportCsv regionSupportCsv : regionSupportCsvList){
            System.out.println(regionSupportCsv);
        }

        Assert.assertEquals(98, regionSupportCsvList.size());
    }
}
