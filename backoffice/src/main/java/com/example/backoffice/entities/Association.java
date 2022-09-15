package com.example.backoffice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Association {
    @NotNull @NotEmpty
    String user;
    @NotNull @NotEmpty
    String device;
}
