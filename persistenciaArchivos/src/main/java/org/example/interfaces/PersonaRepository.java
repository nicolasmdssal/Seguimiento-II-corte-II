package org.example.interfaces;

import org.example.domain.Persona;

import java.util.List;

public interface PersonaRepository {
    List<Persona> findAll();
    Persona findById(int id);
    void save(Persona persona);
    void update(Persona persona);
    void delete(int id);
}