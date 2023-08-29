package com.api.egg.servicios;

import com.api.egg.entidades.Persona;
import com.api.egg.repositorios.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicio {
    
    @Autowired
    private PersonaRepositorio pr;
    
    public Persona save(Persona persona) throws Exception{
        validate(persona);
        Persona p = pr.findByDocumento(persona.getDocumento());
        if(p != null){
            throw new Exception("Documento existente");
        }
        return pr.save(persona);
    }
    
    
    public Persona update(Integer id, Persona persona) throws Exception{
        validate(persona);
        Persona p = pr.getById(id);
        p.setEdad(persona.getEdad());
        p.setNombre(persona.getNombre());
        return pr.save(p);
    }
    
    public void delete(Integer id){
        pr.deleteById(id);
    }
    
    public Persona findByDocumento(Long dni){
        return pr.findByDocumento(dni);
    }
    
    public List<Persona> findByNombreOrEdad(String nombre, Integer edad){
        return pr.findAllByNombreOrEdad(nombre, edad);
    }
    
    public List<Persona> findAll(){
        return pr.findAll();
    }
    
    public Persona findById(Integer id){
        return pr.getById(id);
    }
    
    public void validate(Persona persona) throws Exception{
        if(persona.getNombre().isEmpty() || persona.getNombre() == null){
            throw new Exception("Nombre Invalido");
        }
        
        if(persona.getEdad() == null || persona.getEdad()< 0){
            throw new Exception("Edad Invalida");
        }
        
        if(persona.getDocumento() == null || persona.getDocumento()< 1){
            throw new Exception("Documento Invalido");
        }
        
    }
    
}
