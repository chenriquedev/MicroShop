package org.henrique.java.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String descricao;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float preco;
    @NotNull
    private CategoryDTO category;
}
