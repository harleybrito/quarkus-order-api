package org.acme.dto;

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
public class CustomerDTO {
  private String name;
  private String phone;
  private String email;
  private String address;
  private Long age;
}
