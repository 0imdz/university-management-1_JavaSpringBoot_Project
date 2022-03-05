package com.eep.controller;

import com.eep.entity.Usuarios;
import com.eep.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cuibitcoin")
public class LoginController {

    @Autowired
    @Qualifier("UsuariosService")
    private UsuariosService usuariosService;

    //FORMULARIO QUE BUSCA POR NOMBRE DE USUARIO Y PASSWORD

    @GetMapping("/iniciosesion")
    public String inicioSesion(Model model){
        model.addAttribute("usuarios", new Usuarios());
        return "login";
    }

    @GetMapping("/usuarioenviado")
    public String envioDatos(@RequestParam String nombreusuario, String password,
                             @ModelAttribute("usuarios") Usuarios usuarios,
                             Model model){

        if(usuariosService.checkExistencia(nombreusuario, password)==true){

            Usuarios usuario1=usuariosService.devolucionUsuarioCompleto(nombreusuario, password);
            model.addAttribute("usuario1", usuario1);

            return "inicioapto";
        }else{
            System.out.println("mal");
        }
        return "login";
    }
}
