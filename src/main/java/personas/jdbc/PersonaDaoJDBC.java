package personas.jdbc;

import java.sql.*;

import personas.dto.PersonaDTO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PersonaDaoJDBC implements PersonaDao {
    private Connection ct;
    private final String SQL_INSERT="INSERT INTO personas(nombre, apellido) VALUES (?,?)";
    private final String SQL_UPDATE="UPDATE personas SET nombre=?, apellido=? WHERE Id=?";
    private final String SQL_DELETE="DELETE FROM personas WHERE Id=?";
    private final String SQL_SELECT="SELECT Id ,nombre, apellido FROM personas";
    
    public PersonaDaoJDBC(){}
    public PersonaDaoJDBC(Connection ct){
    this.ct=ct;}
    
   
    
    
    
    @Override
    public int insert(PersonaDTO persona) throws SQLException {
    int rows=0;
    Connection conn=null;
    PreparedStatement stmt = null;

    try { 
        conn=this.ct!=null?this.ct:Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_INSERT);
                stmt.setString(1, "Carlos");
                stmt.setString(2, "Perez");
                System.out.println("Ejecutando Query " + SQL_INSERT);
                        rows=stmt.executeUpdate();
                        System.out.println("Numero de Registros afectados = " +rows);
}
finally {
    Conexion.close(stmt);
        if(this.ct==null){Conexion.close(conn);}
    

}
return rows;

}

@Override
public int update(PersonaDTO persona)throws SQLException{
int rows=0;
Connection conn=null;
PreparedStatement stmt=null;

    try
    {
        conn=this.ct!=null?this.ct:Conexion.getConnection();
        stmt=conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, persona.getId());
            System.out.println("Ejecutando Query "+SQL_UPDATE);
                rows=stmt.executeUpdate();
                System.out.println("Numero de Registros Afectados" +rows );
}
finally
{
    Conexion.close(stmt);
        if(this.ct==null){ Conexion.close(conn);}
}

return rows;

}

@Override
public int delete (PersonaDTO persona) throws SQLException{
Connection conn=null;
PreparedStatement stmt=null;
int rows=0;

try{
    conn=this.ct!=null?this.ct:Conexion.getConnection();
    stmt=conn.prepareStatement(SQL_DELETE);
    stmt.setInt(1,persona.getId());
        System.out.println("Ejecutando Query " +SQL_DELETE );
            rows=stmt.executeUpdate();
            System.out.println("Numero de Registros Afectados" +rows );

}
finally
{
    Conexion.close(stmt);
        if (this.ct==null){Conexion.close(conn);}
}        
return rows;        
}
            




    @Override
    public List<PersonaDTO> select()
{
Connection conn=null;
PreparedStatement stmt=null;
ResultSet rs=null;
PersonaDTO persona=null;
List<PersonaDTO> personas=new ArrayList<>();

try{ conn=ct!=null?this.ct:Conexion.getConnection();
    stmt=conn.prepareStatement(SQL_SELECT);
    rs=stmt.executeQuery();
    
   
    while (rs.next()){
        int Id=rs.getInt("Id");
        String nombre=rs.getString("nombre");
        String apellido=rs.getString("apellido");
        
         persona =new PersonaDTO();
         persona.setNombre(nombre);
         persona.setId(Id);
         persona.setApellido(apellido);
         personas.add(persona);
    }
    }     
        catch (SQLException ex) {
            Logger.getLogger(PersonaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }    finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.ct==null){
            Conexion.close(conn);
        }
        }

        return personas;
    }
}




    

