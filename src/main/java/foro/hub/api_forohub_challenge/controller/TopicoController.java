package foro.hub.api_forohub_challenge.controller;

import foro.hub.api_forohub_challenge.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        System.out.println(datosRegistroTopico);
       Topico topico= topicoRepository.save(new Topico(datosRegistroTopico));
        URI url= uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(topico);
    }

    @GetMapping
    public Page<DatosListadoTopico> listadoTopicos(@PageableDefault(size = 10,sort = "fecha",direction = Sort.Direction.ASC)Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(
                    topico.get().getId(),
                    topico.get().getTitulo(),
                    topico.get().getMensaje(),
                    topico.get().getFecha(),
                    topico.get().getStatus(),
                    topico.get().getAutor(),
                    topico.get().getCurso()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizacionTopico datosActualizacionTopico){
        Optional<Topico> topico=topicoRepository.findById(id);
        if (topico.isPresent()){
            Topico topicoActualizado= topico.get();
            topicoActualizado.setStatus(datosActualizacionTopico.status());
            topicoRepository.save(topicoActualizado);
            return ResponseEntity.ok(new DatosDetalleTopico(topicoActualizado.getId(),topicoActualizado.getTitulo(),topicoActualizado.getMensaje(),
                    topicoActualizado.getFecha(),topicoActualizado.getStatus(),topicoActualizado.getAutor(),
                    topicoActualizado.getCurso()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional<Topico> topico=topicoRepository.findById(id);
        if (topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}