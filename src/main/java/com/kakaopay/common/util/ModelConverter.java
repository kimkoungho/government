package com.kakaopay.common.util;


import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelConverter {

    private ModelMapper modelMapper;

    @PostConstruct
    public void setUp(){
        modelMapper = new ModelMapper();
        // 필드 이름이 완벽히 일치 해야함
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <T> T map(Object source, Class<T> clazz){
        return source == null ? null : modelMapper.map(source, clazz);
    }

    public <T> List<T> listMap(List sourceList, Class<T> clazz){
        if(CollectionUtils.isEmpty(sourceList)){
            return new ArrayList<>();
        }

        List<T> list = new ArrayList<>();
        for(Object source : sourceList){
            list.add(this.map(source, clazz));
        }

        return list;
    }
}
