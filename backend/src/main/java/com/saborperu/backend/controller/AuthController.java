package com.saborperu.backend.controller;

import com.saborperu.backend.dto.AuthResponse;
import com.saborperu.backend.dto.LoginRequest;
import com.saborperu.backend.dto.RegisterRequest;
import com.saborperu.backend.model.Usuario;
import com.saborperu.backend.security.JwtUtil;
import com.saborperu.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para login y registro con JWT")
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener token JWT")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Validar credenciales
            Usuario usuario = usuarioService.login(request.getEmail(), request.getPassword());
            
            // Generar token JWT
            String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
            
            // Devolver respuesta con token
            AuthResponse response = new AuthResponse(
                token,
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getRol(),
                "Login exitoso"
            );
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Credenciales inválidas"));
        }
    }
    
    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario y obtener token JWT")
    public ResponseEntity<?> registro(@RequestBody RegisterRequest request) {
        try {
            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(request.getNombre());
            nuevoUsuario.setEmail(request.getEmail());
            nuevoUsuario.setPassword(request.getPassword());
            nuevoUsuario.setRol("USER"); // Por defecto rol USER
            
            Usuario usuarioCreado = usuarioService.crear(nuevoUsuario);
            
            // Generar token JWT
            String token = jwtUtil.generateToken(usuarioCreado.getEmail(), usuarioCreado.getRol());
            
            // Devolver respuesta con token
            AuthResponse response = new AuthResponse(
                token,
                usuarioCreado.getEmail(),
                usuarioCreado.getNombre(),
                usuarioCreado.getRol(),
                "Registro exitoso"
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/verificar")
    @Operation(summary = "Verificar si el token es válido")
    public ResponseEntity<?> verificarToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("valido", false, "mensaje", "Token no proporcionado"));
            }
            
            String token = authHeader.substring(7);
            
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                String rol = jwtUtil.extractRol(token);
                
                return ResponseEntity.ok(Map.of(
                    "valido", true,
                    "email", email,
                    "rol", rol
                ));
            } else {
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("valido", false, "mensaje", "Token expirado o inválido"));
            }
            
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("valido", false, "mensaje", "Token inválido"));
        }
    }
}


