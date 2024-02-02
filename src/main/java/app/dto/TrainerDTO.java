package app.dto;

import java.time.LocalDate;

public record TrainerDTO(Long id, String name, LocalDate birthday, String nationality, String favouriteStrategy) {

}
