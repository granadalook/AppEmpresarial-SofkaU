package co.com.sofka.questions.security.services;

import co.com.sofka.questions.security.collections.Rol;
import co.com.sofka.questions.security.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired(required=true)
    RolRepository rolRepository;

    public Rol getByRolName(String rolName){
            return rolRepository.findByrolName(rolName);
    }
}
