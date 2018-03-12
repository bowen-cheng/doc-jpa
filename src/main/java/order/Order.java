package order;

import customer.Customer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  @GeneratedValue(generator = "orders_order_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(allocationSize = 1, name = "orders_order_id_seq")
  private long id;

  private String itemName;

  private int quantity;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod = PaymentMethod.UNKNOWN;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
