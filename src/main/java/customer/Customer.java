package customer;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import order.Order;

@Data
@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @Column(name = "customer_id")
  @GeneratedValue(generator = "customers_customer_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(allocationSize = 1, name = "customers_customer_id_seq")
  private long id;

  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
  private List<Order> orders;
}
