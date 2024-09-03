package com.food.ordering.system.restaurant.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

  private String name;
  private Money price;
  private final int quantity;
  private boolean isAvailable;

  public void updateWithConfirmedNamePriceAndAvailability(String name, Money price, boolean isAvailable) {
    this.name = name;
    this.price = price;
    this.isAvailable = isAvailable;
  }

  private Product(Builder builder) {
    setId(builder.productId);
    this.name = builder.name;
    this.price = builder.price;
    this.quantity = builder.quantity;
    this.isAvailable = builder.isAvailable;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getName() {
    return name;
  }

  public Money getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public static class Builder {

    private ProductId productId;
    private String name;
    private Money price;
    private int quantity;
    private boolean isAvailable;

    public Builder productId(ProductId productId) {
      this.productId = productId;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder price(Money price) {
      this.price = price;
      return this;
    }

    public Builder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder isAvailable(boolean isAvailable) {
      this.isAvailable = isAvailable;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }
}
