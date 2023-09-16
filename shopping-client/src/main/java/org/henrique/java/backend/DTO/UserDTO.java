package org.henrique.java.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String nome ;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private String key;
}
