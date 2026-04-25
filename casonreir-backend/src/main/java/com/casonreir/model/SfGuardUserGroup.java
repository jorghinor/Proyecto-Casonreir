package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "sf_guard_user_group")
@IdClass(SfGuardUserGroupId.class)
@Data
public class SfGuardUserGroup implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private SfGuardUser user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private SfGuardGroup group;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
