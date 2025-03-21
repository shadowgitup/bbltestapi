package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateUserReq {

    @NotNull(message = "User Id cannot be null")
    private Integer userId;

    private Integer id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Body cannot be null")
    private String body;

}
