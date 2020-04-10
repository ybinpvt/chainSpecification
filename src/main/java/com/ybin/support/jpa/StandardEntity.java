package com.ybin.support.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class  StandardEntity<Self extends StandardEntity<Self>> {
    @Id
    @GeneratedValue
    private Long id;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime whenCreated;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime whenUpdated;

    @SuppressWarnings("unchecked")
    public Self setId(Long id) {
        this.id = id;
        return (Self) this;
    }
}
