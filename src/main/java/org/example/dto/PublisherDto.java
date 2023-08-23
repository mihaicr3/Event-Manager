package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {


    private int id;

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String description;
    @NotEmpty
    private String role;
}
