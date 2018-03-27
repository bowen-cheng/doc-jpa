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

/**
 * The @Entity annotation tells Hibernate this Object should be a table in the database.
 *
 * The @Table can be used for customizing the table
 */
@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
public class Customer {

  /**
   * Be aware that the setters are used in the constructor so that the fields (orders) can be
   * initialized.
   *
   * @param name Name of the customer
   * @param orders The orders belonging to this customer
   */
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

  /**
   * The "mappedBy" property defines the Java field inside type Order that maps this collection type
   * field.
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private List<Order> orders;

  /**
   * The "orders" collection must be initialized in the setter so that all objects/records of the
   * Orders has its foreign key (customer_id) set properly.
   *
   * @param orders The orders belonging to this customer
   */
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
