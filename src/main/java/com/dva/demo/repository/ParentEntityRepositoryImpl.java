package com.dva.demo.repository;

import com.dva.demo.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class ParentEntityRepositoryImpl<T extends BaseEntity>
        extends SimpleJpaRepository<T, Long>
        implements ParentEntityRepository<T> {

    private final EntityManager entityManager;

    @Autowired
    public ParentEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void markDeleted(T entity) {
        entity = entityManager.merge(entity);
        entity.setDeleted(true);
        entityManager.persist(entity);
    }

}
