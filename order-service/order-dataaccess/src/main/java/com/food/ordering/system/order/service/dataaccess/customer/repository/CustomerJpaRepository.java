package com.food.ordering.system.order.service.dataaccess.customer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

}
