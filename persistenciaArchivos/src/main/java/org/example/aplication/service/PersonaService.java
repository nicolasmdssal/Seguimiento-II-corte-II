package org.example.aplication.service;

import org.example.domain.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> findAll();
    Persona findById(int id);
    void save(Persona persona);
    void update(Persona persona);
    void delete(int id);
}