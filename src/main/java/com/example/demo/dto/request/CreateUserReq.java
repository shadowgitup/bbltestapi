package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserReq {

    @NotNull(message = "User Id cannot be null")
    private int userId;

    @NotNull(message = "Id cannot be null")
    private int id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Body cannot be null")
    private String body;
    
}
