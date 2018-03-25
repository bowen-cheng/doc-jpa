package com.doc.onetomany.customer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Transactional(readOnly = true)
  List<Customer> getByName(String name);

  @Transactional
  void deleteByName(String name);
}
