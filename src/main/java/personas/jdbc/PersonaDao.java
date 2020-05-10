package personas.jdbc;
import java.sql.SQLException;
import personas.dto.PersonaDTO;
import java.util.*;
public interface PersonaDao {


public int insert(PersonaDTO persona)throws SQLException;

public int update(PersonaDTO persona)throws SQLException;

public int delete (PersonaDTO persona)throws SQLException;

public List<PersonaDTO> select()throws SQLException;
}
