package mx.com.gm.dao;
import mx.com.gm.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//se crea la interface personaDao
//vamos hacerla de repositorio y ya no es necesario crear una implementacion 
//con el metodo Crud tenemos los metodos mas basicos 
    
public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}

