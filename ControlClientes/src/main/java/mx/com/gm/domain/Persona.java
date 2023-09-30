//Validaciones
package mx.com.gm.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
//se convierte la clase en una clase de entidad
@Entity
@Table(name="persona")
public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idPersona;
    
    //para crear validaciones, para que no existan datos nulos ya sean cadenas o valores
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email
    private String email;
    
    
    private String telefono;
    
    @NotNull
    private Double saldo;
}
