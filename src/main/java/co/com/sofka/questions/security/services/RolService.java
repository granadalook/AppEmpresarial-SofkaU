package co.com.sofka.questions.security.services;

import co.com.sofka.questions.security.collections.Rol;
import co.com.sofka.questions.security.enums.RolName;
import co.com.sofka.questions.security.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
