package com.henrique.java.repository;

import com.henrique.java.entity.Shop;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Primary
public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository {

    List<Shop> findAllByUserIdentifier(String userIdentifier);
    List<Shop> findAllByTotalGreaterThan(Float total);
    List<Shop> findAllByDateGreaterThanEqual(Date date);

}
