package com.kakaopay.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseQueryDslSupport extends QuerydslRepositorySupport {

    protected JPAQueryFactory jpaQueryFactory;
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public BaseQueryDslSupport(Class<?> domainClass) {
        super(domainClass);
    }

    // hibernate entity manager 등록
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        super.setEntityManager(entityManager);

        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
}
