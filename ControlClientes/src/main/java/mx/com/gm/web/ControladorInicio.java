package mx.com.gm.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import mx.com.gm.servicio.PersonaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;

@Controller
@Slf4j
public class ControladorInicio {

    //la interfaz de personaDao la inyectamos en nuestro controlador con el autowired
    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        //con el metodo ya inyectado podemos hacer uso del findAll
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        var saldoTotal = 0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        //con el size nos manda el total de clientes que hay en personas
        model.addAttribute("totalClientes", personas.size());
        //recuperamos y compartimos esta interfaz de personas con el model
        
        //recuperamos y compartimos esta interfaz de personas con el model
        model.addAttribute("personas", personas);
        return "index";
    }

    //vamos a hacer el path de agregar
    @GetMapping("/agregar")
    //para que nos inyecte un objeto de tipo persona solamento lo agergamos como un argumento en nuestro metodo 
    public String agregar(Persona persona) {
        //nuestra vista de modificar va agregar y modificar al mismo tiempo 
        return "modificar";
    }

    @PostMapping("/guardar")
    //vamos a agregar validacion con el @Valid
    //recuperamos los errores con Errors
    //la validacion y el manejo de errores siempre deben estar juntos
    public String guardar(@Valid Persona persona, Errors errores) {
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        //lo redireccionamos a la pagina de inicio
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
