import Entidades.*;
import Repositorio.InMemoryRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();

        Pais argentina = Pais.builder().nombre("Argentina").build();
        Provincia buenosaires = Provincia.builder().nombre("Buenos Aires").id(1L).pais(argentina).build();

        Localidad caba = Localidad.builder().nombre("CABA").id(1L).provincia(buenosaires).build();
        Domicilio domicilio1 = Domicilio.builder().calle("Avenida La Plata").cp(1259).numero(1700).localidad(caba).build();
        Sucursal sucursal1 = Sucursal.builder().nombre("Sucursal 1").id(1L).horarioApertura(LocalTime.of(8,0)).horarioCierre(LocalTime.of(17,0)).es_Casa_Matriz(true).build();



        Localidad laplata = Localidad.builder().nombre("La Plata").id(2L).provincia(buenosaires).build();
        Domicilio domicilio2= Domicilio.builder().calle("Mariano Moreno").cp(1900).numero(2000).localidad(laplata).build();
        Sucursal sucursal2 = Sucursal.builder().nombre("Sucursal 2").id(2L).horarioApertura(LocalTime.of(9,0)).horarioCierre(LocalTime.of(18,0)).es_Casa_Matriz(false).build();

        Provincia cordoba = Provincia.builder().nombre("Cordoba").id(2L).pais(argentina).build();

        Localidad cordobacapital = Localidad.builder().nombre("Cordoba Capital").id(3L).provincia(cordoba).build();
        Domicilio domicilio3 = Domicilio.builder().calle("General San Martin").cp(5000).numero(500).localidad(cordobacapital).build();
        Sucursal sucursal3 = Sucursal.builder().nombre("Sucursal 3").id(3L).horarioApertura(LocalTime.of(8,0)).horarioCierre(LocalTime.of(17,0)).es_Casa_Matriz(true).build();

        Localidad villacarlospaz = Localidad.builder().nombre("Villa Carlos Paz").id(4L).provincia(cordoba).build();
        Domicilio domicilio4 = Domicilio.builder().calle("Mario Alberto Kempes").cp(5152).numero(320).localidad(villacarlospaz).build();
        Sucursal sucursal4 = Sucursal.builder().nombre("Sucursal 4").id(4L).horarioApertura(LocalTime.of(9,0)).horarioCierre(LocalTime.of(18,0)).es_Casa_Matriz(false).build();

        Empresa empresa1 = Empresa.builder().nombre("Empresa 1").razonsocial("Razon Social 1").cuit(2321421412L).sucursales(new HashSet<>(Set.of(sucursal1, sucursal2))).build();
        Empresa empresa2 = Empresa.builder().nombre("Empresa 2").razonsocial("Razon Social 2").cuit(1234354556L).sucursales(new HashSet<>(Set.of(sucursal3, sucursal4))).build();

        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);
        //Aca se muestran todas las empresas
        System.out.println("Empresas:");
        List<Empresa> todaslasEmpresas = empresaRepository.findAll();
        todaslasEmpresas.forEach(System.out::println);
        //Buscar las empresas por su ID
        Optional<Empresa> empresabuscada = empresaRepository.findById(1L);
        empresabuscada.ifPresent(e -> System.out.println("Empresa encontrada por ID 1:" + e));
        // Buscar empresa por nombre
        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("Nombre", "Empresa 1");
        System.out.println("Empresas con nombre 'Empresa 1':");
        empresasPorNombre.forEach(System.out::println);

        // Actualizar empresa por ID
        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .nombre("Empresa 1 Actualizada")
                .razonsocial("Razon Social 1 Actualizada")
                .cuit(12345678901L)
                .sucursales(empresa1.getSucursales())
                .build();

        empresaRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("Empresa después de la actualización: " + e));

        // Eliminar empresa por ID
        empresaRepository.genericDelete(1L);
        Optional<Empresa> empresaEliminada = empresaRepository.findById(1L);
        if (empresaEliminada.isEmpty()) {
            System.out.println("La empresa con ID 1 se elimino.");
        }

        // Mostrar todas las empresas restantes
        System.out.println("Todas las empresas después de la eliminación:");
        List<Empresa> empresasRestantes = empresaRepository.findAll();
        empresasRestantes.forEach(System.out::println);
        System.out.println("--------------Mostrar las sucursales de una empresa determinada");
        // Mostrar las sucursales de una empresa deerminada
        Optional<Empresa> empresa = empresaRepository.findById(2L);
        if (empresa.isPresent()) {
            System.out.println("Sucursales de la empresa con ID "  + ":");
            Set<Sucursal> sucursales = empresa.get().getSucursales();
            sucursales.forEach(System.out::println);
        } else {
            System.out.println("Empresa con ID " + " no encontrada.");
        }

    }
}