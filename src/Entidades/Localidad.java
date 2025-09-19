package Entidades;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Localidad {
    private String nombre;
    private long id;
    private Provincia provincia;

    @Override
    public String toString(){
        return "Localidad{" + "id=" + id + ", nombre='" + nombre + '\'' + ", proviencia=" + (provincia != null ? provincia.getNombre() : null) + '}';
    }
}
