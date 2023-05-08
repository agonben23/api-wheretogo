package com.example.controllers

import com.example.models.Ciudad
import com.example.models.Usuario
import com.example.services.UsuariosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
//TODAS LAS PETICIONES QUE LLEGUEN A "/api/v1/productos"
@RequestMapping("/api/v1/usuarios")
@CrossOrigin("*")
class UsuariosController() {

    @Autowired
    lateinit var usuariosService: UsuariosService

    @GetMapping("/all")
    fun getAll() : ResponseEntity<List<Usuario>>{
        return try {

            val lisUsers = usuariosService.all

            ResponseEntity(lisUsers, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/one")
    fun getOne(@RequestBody usuario: Usuario): ResponseEntity<Usuario?> {

        return try {
            val user = usuario

            val lisUsers = usuariosService.all

            val response =
                lisUsers.find { (it.mail == user.mail || it.nick == user.nick) && it.password == user.password }

            ResponseEntity(response, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }

    }

    @PostMapping("/")
    fun insertUser(@RequestBody usuario: Usuario): ResponseEntity<Usuario?> {

        return try {
            val insertado = usuariosService.save(usuario)
            ResponseEntity(insertado, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }

    }


}