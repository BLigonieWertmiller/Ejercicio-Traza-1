package Entidades;
import lombok.*;
import lombok.experimental.SuperBuilder;




@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "localidad")  // Excluir localidad para evitar recursión infinita
@SuperBuilder

public class Domicilio {
    private String calle;
    private Integer numero;
    private Integer cp;

    private Localidad localidad;
}
