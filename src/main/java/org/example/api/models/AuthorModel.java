package org.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorModel {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}
