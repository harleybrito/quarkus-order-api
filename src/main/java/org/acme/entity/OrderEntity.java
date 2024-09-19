package org.acme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orderProducts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
  @Id
  @GeneratedValue
  private Long orderId;
  private Long customerId;
  private Long productId;
  private String customerName;
  private BigDecimal orderValue;
}
