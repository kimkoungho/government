package com.kakaopay.jpa.repository;


import com.kakaopay.jpa.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RegionRepository extends JpaRepository<RegionEntity, String> {

    Optional<RegionEntity> findByName(String name);
}
