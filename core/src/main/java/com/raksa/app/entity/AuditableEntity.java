package com.raksa.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {

    @DateTimeFormat(pattern = "yyyy-MMM-dd HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MMM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Phnom_Penh"));
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now(ZoneId.of("Asia/Phnom_Penh"));
    }
}
