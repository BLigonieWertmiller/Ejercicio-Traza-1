package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "sucursales")  // Excluir sucursales para evitar recursión infinita
@SuperBuilder

public class Empresa {
    private String nombre;
    private String razonsocial;
    private long cuit;
    private Long id;

    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

}
