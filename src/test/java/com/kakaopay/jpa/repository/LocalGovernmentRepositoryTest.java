package com.kakaopay.jpa.repository;


import com.kakaopay.jpa.entity.LocalGovernmentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext.xml"
})
public class LocalGovernmentRepositoryTest {

    @Autowired
    private LocalGovernmentRepository localGovernmentRepository;

    @Test
    public void test(){
        LocalGovernmentEntity localGovernmentEntity = new LocalGovernmentEntity();
        localGovernmentEntity.setGovernmentCode("123");
        localGovernmentEntity.setGovernmentName("수원");

        localGovernmentEntity = localGovernmentRepository.save(localGovernmentEntity);

        System.out.println(localGovernmentEntity);
    }
}