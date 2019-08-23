package com.kakaopay.jpa.repository;

import java.util.List;

public interface RegionSupportRepositoryCustom {

    List<String> getRegionNameListBySorted(Integer count);

    String getMinAvgRate();
}
