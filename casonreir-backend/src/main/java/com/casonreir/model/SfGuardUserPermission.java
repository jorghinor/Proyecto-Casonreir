package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "sf_guard_user_permission")
@Data
public class SfGuardUserPermission {

    @EmbeddedId
    private SfGuardUserPermissionId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private SfGuardUser user;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private SfGuardPermission permission;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Embeddable
    @Data
    public static class SfGuardUserPermissionId implements Serializable {
        private Long userId;
        private Long permissionId;
    }
}
