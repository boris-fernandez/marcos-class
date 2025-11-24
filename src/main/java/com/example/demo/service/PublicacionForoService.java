package com.example.demo.service;

import com.example.demo.domain.TemaForo.TemaForo;
import com.example.demo.domain.TemaForo.TemaForoRepository;
import com.example.demo.domain.publicacionForo.PublicacionForo;
import com.example.demo.domain.publicacionForo.PublicacionForoRepository;
import com.example.demo.domain.publicacionForo.dto.CrearPublicacionForoDTO;
import com.example.demo.domain.publicacionForo.dto.PublicacionForoDTO;
import com.example.demo.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PublicacionForoService {

    @Autowired
    private PublicacionForoRepository publicacionForoRepository;

    @Autowired
    private TemaForoRepository temaForoRepository;

    public Usuario getUsuarioActual() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //CREAR PUBLICACION FORO
    public PublicacionForoDTO crearPublicacionForo(CrearPublicacionForoDTO crearPublicacionForoDTO){
        TemaForo temaForo = temaForoRepository.findById(crearPublicacionForoDTO.temaForo()).orElseThrow(()-> new RuntimeException("No existe un foro con ese id: " + crearPublicacionForoDTO.temaForo()));
        Usuario usuario = getUsuarioActual();

        PublicacionForo publicacionForo = PublicacionForo.builder()
                .temaForo(temaForo)
                .usuario(usuario)
                .contenido(crearPublicacionForoDTO.contenido())
                .fechaPublicacion(new Date())
                .build();
        publicacionForoRepository.save(publicacionForo);

        return new PublicacionForoDTO(publicacionForo);
    }

    //LISTAR PUBLICACION EN UN FORO
    public List<PublicacionForoDTO> listarPublicacionPorForo(Long idForo){
        return publicacionForoRepository.findAllByTemaForo_IdTema(idForo).stream().map(PublicacionForoDTO::new).toList();
    }
}
