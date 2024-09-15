package com.PPOOII.Laboratorio.Repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PPOOII.Laboratorio.Entities.Persona;

@Repository("IPersonaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Integer>, CrudRepository<Persona, Integer>{
	
	//Hay Métodos que JPA ya los tiene desarrollados, se pueden crear para tener
	//una manipulación más especifica a la hora de usarlos en el service	


    Persona findById(int id);
    
    
    Persona findByIdentificacion( int identificacion);

    
    Page<Persona> findAll(Pageable pageable);
	   
    List<Persona> findByPnombre(String pnombre);

  
    List<Persona> findBySNombre( String SNombre);

   
    List<Persona> findByPApellido(@Param("PApellido") String PApellido);

  
    List<Persona> findBySApellido(@Param("SApellido") String SApellido);

    
    List<Persona> findByEmail(@Param("email") String email);

   
    List<Persona> findByFechaNacimiento(@Param("fechaNacimiento") LocalDate fechaNacimiento);

   
    List<Persona> findByEdad(@Param("edad") int edad);

   
    List<Persona> findByEdadClinica(@Param("edadClinica") String edadClinica);

}
