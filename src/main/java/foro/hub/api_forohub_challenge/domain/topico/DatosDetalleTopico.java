package foro.hub.api_forohub_challenge.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status,
        String autor,
        String curso
) {
}
