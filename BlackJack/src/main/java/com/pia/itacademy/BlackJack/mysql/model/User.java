package com.pia.itacademy.BlackJack.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(
        name = "user"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private @NotBlank(
            message = "Please add the user name"
    ) String name;

    public User(String name) {
        this.name = name;
    }

    public @NotBlank(
            message = "Please add the user name"
    ) String getName() {
        return name;
    }

    public void setName(@NotBlank(
            message = "Please add the user name"
    ) String name) {
        this.name = name;
    }
}
