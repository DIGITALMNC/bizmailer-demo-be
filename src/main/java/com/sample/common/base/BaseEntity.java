package com.sample.common.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Getter
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tbl-gen")
    @TableGenerator(name = "tbl-gen", pkColumnName = "entity_tbl_name", allocationSize = 10, table = "entity_ids")
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public BaseEntity() {}

    public BaseEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
