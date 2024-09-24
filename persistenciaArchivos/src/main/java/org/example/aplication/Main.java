package org.example.aplication;

import org.example.aplication.service.PersonaService;
import org.example.aplication.service.PersonaServiceImpl;
import org.example.domain.Persona;
import org.example.infraestructure.repository.PersonaRepositoryImpl;
import org.example.interfaces.PersonaRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PersonaService PERSONA_SERVICE;

    static {
        PersonaRepository personaRepository = new PersonaRepositoryImpl();
        PERSONA_SERVICE = new PersonaServiceImpl(personaRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println(" ");
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Crear persona");
            System.out.println("2. Actualizar contraseña");
            System.out.println("3. Listar personas");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearPersona();
                    break;
                case 2:
                    actualizarContraseña();
                    break;
                case 3:
                    listarPersonas();
                    break;
                case 4:
                    eliminarPersona();
                    break;
                case 5:
                    salir = true;
                    System.out.println("hasta luego");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void crearPersona() {
        System.out.print("Ingrese el còdigo del persona: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la contraseña (cuatro digitos): ");
        String contraseña = scanner.nextLine();

        Persona persona = new Persona();
        persona.setId(cod);
        persona.setNombre(nombre);
        persona.setContraseña(contraseña);

        try {
            PERSONA_SERVICE.save(persona);
            System.out.println("Persona creada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarContraseña() {
        System.out.print("Ingrese el ID del persona a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Persona persona = PERSONA_SERVICE.findById(id);
        if (persona == null) {
            System.out.println("No se encontró la persona con ID " + id);
            return;
        }

        System.out.print("Ingrese la nueva contraseña de usuairo(debe tener 4 digitos): ");
        String contraseña = scanner.nextLine();
        scanner.nextLine(); // Consumir el salto de línea
            persona.setContraseña(contraseña);

        try {
            PERSONA_SERVICE.update(persona);
            System.out.println("Persona actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarPersonas() {
        List<Persona> personas = PERSONA_SERVICE.findAll();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            System.out.println("Listado de personas:");
            for (Persona persona : personas) {
                System.out.println(persona);
            }
        }
    }

    private static void eliminarPersona() {
        System.out.print("Ingrese el ID de la persona a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Persona persona = PERSONA_SERVICE.findById(id);
        if (persona == null) {
            System.out.println("No se encontró la persona con ID " + id);
            return;
        }

        PERSONA_SERVICE.delete(id);
        System.out.println("Usuario eliminado exitosamente.");
    }

}
