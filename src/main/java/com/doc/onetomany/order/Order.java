package com.doc.onetomany.order;

import com.doc.onetomany.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @Column(name = "order_id")
  @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(allocationSize = 1, name = "id_seq", sequenceName = "orders_order_id_seq")
  @JsonIgnore
  private long id;

  private String itemName;

  private int quantity;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod = PaymentMethod.UNKNOWN;

  /**
   * By default, Hibernate uses a reference table for one to many mappings, @JoinColumn tells
   * Hibernate to use a column instead.
   *
   * @JsonIgnore is needed here to prevent stack over flow exception during serialization (prevent
   * cyclic serialization).
   *
   * @ManyToMany corresponds to the @OneToMany on the other side. This forms a bidirectional
   * relationship. Bidirectional relationship works more efficient than single directional
   * relationships. FetchType.LAZY tells Hibernate to lazily load the Orders.
   */
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
