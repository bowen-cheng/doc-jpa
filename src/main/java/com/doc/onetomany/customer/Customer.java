package com.doc.onetomany.customer;

import com.doc.onetomany.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.val;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class Customer {

  Customer(@NonNull String name, @NonNull List<Order> orders) {
    setName(name);
    setOrders(orders);
  }

  @Id
  @Column(name = "customer_id")
  @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
    allocationSize = 1,
    name = "id_seq",
    sequenceName = "customers_customer_id_seq"
  )
  @JsonIgnore
  private long id;

  @NonNull
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private List<Order> orders;

  void setOrders(@NonNull List<Order> orders) {
    if (orders.isEmpty()) {
      return;
    }
    for (val order : orders) {
      order.setCustomer(this);
    }
    this.orders = orders;
  }
}
