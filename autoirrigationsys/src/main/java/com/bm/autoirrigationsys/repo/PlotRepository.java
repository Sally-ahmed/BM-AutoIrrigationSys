package com.bm.autoirrigationsys.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.autoirrigationsys.model.PlotEB;

public interface PlotRepository extends JpaRepository<PlotEB, Long> {

}
