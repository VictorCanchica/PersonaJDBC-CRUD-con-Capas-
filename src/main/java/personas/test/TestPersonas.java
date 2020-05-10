package personas.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
import personas.jdbc.*;

public class TestPersonas {
    public static void main(String[] args) {
        Connection conexion = null;
    
        try {
            
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            //establecemos la conexion
            PersonaDao persona=new PersonaDaoJDBC(conexion);
            //metodo Update
            PersonaDTO cambioPersona=new PersonaDTO(1,"Karla","Landa");
            persona.update(cambioPersona);    
            //metodo Insert
            PersonaDTO newPersona=new PersonaDTO("Pablo", "Canchica");
            persona.insert(newPersona);
            //metodo Delete
            PersonaDTO deletePersonaDTO=new PersonaDTO(2);
            persona.delete(deletePersonaDTO);
            //metodo select
            List<PersonaDTO> personas=persona.select();
            for (PersonaDTO persona1:personas){
                System.out.println("PersonaDTO = " +persona1);
            //cerramos la conexcion y guardamos cambios 
            conexion.commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");}
            try{
                conexion.rollback();}
            catch (SQLException exl){
                exl.printStackTrace(System.out);
                
            }
        }
    
    
    
    }

    
    

