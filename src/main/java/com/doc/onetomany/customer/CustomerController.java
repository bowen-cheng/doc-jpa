package com.doc.onetomany.customer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  private static final String TAG = "customers";

  @Autowired private CustomerRepository customerRepository;

  @ApiOperation(value = "Get all customers", tags = TAG)
  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return ResponseEntity.ok(customerRepository.findAll());
  }

  @ApiOperation(value = "Get a customer by name", tags = TAG)
  @GetMapping("/{name}")
  public ResponseEntity<List<Customer>> getByName(
      @ApiParam(value = "Customer name", required = true) @PathVariable String name) {
    val customers = customerRepository.getByName(name);
    if (customers.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(customers);
    }
  }

  @ApiOperation(value = "Create a new customer record", tags = TAG)
  @PostMapping
  public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
  }

  @ApiOperation(value = "Delete an existing customer", tags = TAG)
  @DeleteMapping("/{name}")
  public ResponseEntity<Customer> delete(
      @ApiParam(value = "Customer name", required = true) @PathVariable String name) {
    if (customerRepository.getByName(name).isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      customerRepository.deleteByName(name);
      return ResponseEntity.ok().build();
    }
  }
}
