package com.example.parking.repository;

import com.example.parking.entity.PriceScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceScaleRepo extends JpaRepository<PriceScale,Integer> {
}
