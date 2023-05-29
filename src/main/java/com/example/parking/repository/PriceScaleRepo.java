package com.example.parking.repository;

import com.example.parking.entity.PriceScale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceScaleRepo extends JpaRepository<PriceScale, Integer> {
}
