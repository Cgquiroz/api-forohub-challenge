package foro.hub.api_forohub_challenge.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizacionTopico(
        @NotNull
        Estado status
        ) {
}
