package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {




    private Long id;
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String category;

    @NotEmpty
    private LocalDateTime date_to;

    @NotEmpty

    private LocalDateTime date_from;

    @NotEmpty
    private String location;
    @NotEmpty
    private int publisher_id;
}
