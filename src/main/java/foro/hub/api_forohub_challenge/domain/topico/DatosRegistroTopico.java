package foro.hub.api_forohub_challenge.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Estado status,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
