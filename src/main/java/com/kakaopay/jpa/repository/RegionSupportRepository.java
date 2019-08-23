package com.kakaopay.jpa.repository;

import com.kakaopay.jpa.entity.RegionSupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegionSupportRepository extends JpaRepository<RegionSupportEntity, Long>, RegionSupportRepositoryCustom{

}
