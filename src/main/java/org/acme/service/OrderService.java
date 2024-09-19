package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.acme.client.CustomerClient;
import org.acme.client.ProductClient;
import org.acme.dto.CustomerDTO;
import org.acme.dto.OrderDTO;
import org.acme.entity.OrderEntity;
import org.acme.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class OrderService {
  @Inject
  OrderRepository orderRepository;

  @Inject
  @RestClient
  CustomerClient customerClient;

  @Inject
  @RestClient
  ProductClient productClient;

  public List<OrderDTO> getAllOrders() {
    List<OrderDTO> orders = new ArrayList<>();
    this.orderRepository.findAll().stream().forEach(item -> {
      orders.add(this.entityToDto(item));
    });
    return orders;
  }

  public void saveNewOrder(OrderDTO orderDTO) {
    CustomerDTO customerDTO = this.customerClient.getCustomerById(orderDTO.getCustomerId());
    if(customerDTO.getName().equals(orderDTO.getCustomerName())
        && this.productClient.getProductById(orderDTO.getProductId()) != null) {
      this.orderRepository.persist(this.dtoToEntity(orderDTO));
    } else {
      throw new NotFoundException();
    }
  }

//  public void changeOrder(Long id, OrderDTO order) {
//    OrderEntity orderEntity =  this.orderRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
//    OrderDTO orderDTO = this.entityToDto(orderEntity);
//    CustomerDTO customerDTO = this.customerClient.getCustomerById(orderDTO.getCustomerId());
//    if(customerDTO.getName().equals(orderDTO.getCustomerName()) && this.productClient.getProductById(orderDTO.getProductId()) != null) {
//      this.orderRepository.persist(this.dtoToEntity(orderDTO));
//    } else {
//      throw new NotFoundException();
//    }
//  }

  private OrderEntity dtoToEntity(OrderDTO orderDTO) {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setCustomerId(orderDTO.getCustomerId());
    orderEntity.setProductId(orderDTO.getProductId());
    orderEntity.setOrderValue(orderDTO.getOrderValue());
    orderEntity.setCustomerName(orderDTO.getCustomerName());
    return orderEntity;
  }

  private OrderDTO entityToDto(OrderEntity orderEntity) {
    OrderDTO dto = new OrderDTO();
    dto.setCustomerName(orderEntity.getCustomerName());
    dto.setOrderValue(orderEntity.getOrderValue());
    dto.setCustomerId(orderEntity.getCustomerId());
    dto.setProductId(orderEntity.getProductId());
    return dto;
  }
}
