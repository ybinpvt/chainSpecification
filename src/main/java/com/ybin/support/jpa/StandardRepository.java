package com.ybin.support.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface StandardRepository<T extends StandardEntity<?>, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    <S extends T> List<S> findByWhenCreatedEquals(LocalDateTime whenCreated);

    <S extends T> List<S> findByWhenCreatedBetween(LocalDateTime start, LocalDateTime end);

    <S extends T> Page<S> findByWhenCreatedBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
