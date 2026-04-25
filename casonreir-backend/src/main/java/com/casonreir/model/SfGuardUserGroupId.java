package com.casonreir.model;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SfGuardUserGroupId implements Serializable {
    private Long user;  // Debe coincidir con el nombre del atributo en la entidad
    private Long group; // Debe coincidir con el nombre del atributo en la entidad
}