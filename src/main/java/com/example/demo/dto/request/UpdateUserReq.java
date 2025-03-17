package com.example.demo.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UpdateUserReq {

    @NotNull(message = "User Id cannot be null")
    private Integer userId;

    @NotNull(message = "id Id cannot be null")
    @Min(value = 0, message = "Id must between 0 - 90")
    @Max(value = 90, message = "Id must between 0 - 90")
    private Integer id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Body cannot be null")
    private String body;
    
}
