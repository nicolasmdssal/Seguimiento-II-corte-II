package org.example.aplication;

import org.example.aplication.service.ProductoService;
import org.example.aplication.service.ProductoServiceImpl;
import org.example.domain.Producto;
import org.example.infraestructure.repository.ProductoRepositoryImpl;
import org.example.interfaces.ProductoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductoService productoService;

    static {
        ProductoRepository productoRepository = new ProductoRepositoryImpl();
        productoService = new ProductoServiceImpl(productoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar un nuevo paciente");
            System.out.println("2. Actualizar datos del paciente");
            System.out.println("3. Registrar una nueva cita para un paciente existente");
            System.out.println("4. Eliminar una cita asignada.");
            System.out.println("5. Mostrar la lista de pacientes registrados");
            System.out.println("6. Mostrar la lista de citas registradas para un paciente específico");
            System.out.println("7. salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    actualizarProducto();
                    break;
                case 3:
                    crearCita();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    listarProductos();
                case 6:

                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarProductos() {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de pacientes:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    private static void crearProducto() {
        System.out.print("Ingrese el Id del paciente: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del paciente: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = new Producto();
        producto.setId(cod);
        producto.setNombre(nombre);
        producto.setPrecio(precio);

        try {
            productoService.save(producto);
            System.out.println("Producto creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void crearCita() {
        System.out.println("ingrese el Id del paciente");
    }

    private static void actualizarProducto() {
        System.out.print("Ingrese el ID del paciente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = productoService.findById(id);
        if (producto == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            producto.setNombre(nombre);
        }

        System.out.print("Ingrese la nueva edad (0 para no cambiar): ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        if (precio > 0) {
            producto.setPrecio(precio);
        }

        try {
            productoService.update(producto);
            System.out.println("Paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Producto producto = productoService.findById(id);
        if (producto == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }

        productoService.delete(id);
        System.out.println("Paciente eliminado exitosamente.");
    }
}
