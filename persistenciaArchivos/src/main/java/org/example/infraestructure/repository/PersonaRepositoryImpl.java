package org.example.infraestructure.repository;

import org.example.domain.Persona;
import org.example.interfaces.PersonaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonaRepositoryImpl implements PersonaRepository {
    private static final String FILE_NAME = "productos.dat";

    @Override
    public List<Persona> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Persona>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Persona findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Persona persona) {
        List<Persona> personas = findAll();
        personas.add(persona);
        saveAll(personas);
    }

    @Override
    public void update(Persona persona) {
        List<Persona> personas = findAll();
        personas = personas.stream()
                .map(p -> p.getId() == persona.getId() ? persona : p)
                .collect(Collectors.toList());
        saveAll(personas);
    }

    @Override
    public void delete(int id) {
        List<Persona> personas = findAll();
        personas = personas.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(personas);
    }

    private void saveAll(List<Persona> personas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
