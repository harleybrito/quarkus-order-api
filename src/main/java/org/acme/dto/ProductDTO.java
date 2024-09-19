package org.acme.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
@Data
public class ProductDTO {
  private String name;
  private String description;
  private String category;
  private String model;
  private BigDecimal price;
}
