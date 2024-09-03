package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import com.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDataAccessMapper {

  public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
    return restaurant.getProducts().stream()
        .map(product -> product.getId().getValue())
        .toList();
  }

  public Restaurant restaurantEntitiesToRestaurant(List<RestaurantEntity> restaurantEntities) {
    RestaurantEntity restaurantEntity = restaurantEntities.stream()
        .findFirst()
        .orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found"));

    List<Product> products = restaurantEntities.stream()
        .map(entity -> new Product(new ProductId(entity.getProductId()), entity.getProductName(),
            new Money(entity.getProductPrice())))
        .toList();

    return Restaurant.builder()
        .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
        .products(products)
        .active(restaurantEntity.getRestaurantActive())
        .build();

  }

}
