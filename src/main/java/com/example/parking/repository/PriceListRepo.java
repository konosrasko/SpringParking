package com.example.parking.repository;

import com.example.parking.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceListRepo extends JpaRepository<PriceList,Integer> {

}
