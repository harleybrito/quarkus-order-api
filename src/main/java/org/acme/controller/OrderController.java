package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.dto.OrderDTO;
import org.acme.service.OrderService;

@Path("/api/orders")
public class OrderController {
  @Inject
  OrderService orderService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed({"user", "admin"})
  public List<OrderDTO> getAllOrders(@HeaderParam(value="Authorization") String token) {
    return this.orderService.getAllOrders();
  }

  @POST
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createOrder(OrderDTO order) {
    try {
      this.orderService.saveNewOrder(order);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }
}
