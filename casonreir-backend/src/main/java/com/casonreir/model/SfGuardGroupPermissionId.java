package com.casonreir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SfGuardGroupPermissionId implements Serializable {
    private Long group;      // Debe coincidir con el nombre del atributo en SfGuardGroupPermission
    private Long permission; // Debe coincidir con el nombre del atributo en SfGuardGroupPermission
}
