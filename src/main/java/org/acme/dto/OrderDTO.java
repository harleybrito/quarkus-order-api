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
public class OrderDTO {
  private Long customerId;
  private Long productId;
  private String customerName;
  private BigDecimal orderValue;
}
