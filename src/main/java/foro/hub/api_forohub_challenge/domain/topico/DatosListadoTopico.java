package foro.hub.api_forohub_challenge.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Estado status,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getStatus(),topico.getAutor(),topico.getCurso());
    }
}
