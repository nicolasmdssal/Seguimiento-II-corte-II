package org.example.aplication.service;

import org.example.domain.Persona;
import org.example.interfaces.PersonaRepository;

import java.util.List;

public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona findById(int id) {
        return personaRepository.findById(id);
    }

    @Override
    public void save(Persona persona) {
        validarProducto(persona);
        personaRepository.save(persona);
    }

    @Override
    public void update(Persona persona) {
        validarProducto(persona);
        personaRepository.update(persona);
    }

    @Override
    public void delete(int id) {
        personaRepository.delete(id);
    }

    private void validarProducto(Persona persona) {
        if (persona.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la persona no puede estar vacío");
        }
        if (persona.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
    }
}
