package Entidades;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.swing.plaf.PanelUI;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Provincia {
    private String nombre;
    private long id;

    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();
    private Pais pais;

    @Override
    public String toString(){
        return "Provincia{" + "id=" + id + ", nombre='" + nombre + '\'' + ", pais=" + (pais != null ? pais.getNombre() : null) + '}';
    }

}
