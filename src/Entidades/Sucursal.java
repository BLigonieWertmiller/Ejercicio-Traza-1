package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;




@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "empresa")  // Excluir empresa para evitar recursión infinita
@SuperBuilder

public class Sucursal {
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Matriz;
    private long id;
    private Domicilio domicilio;
    private Empresa empresa;
}
