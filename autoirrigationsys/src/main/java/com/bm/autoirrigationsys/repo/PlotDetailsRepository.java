package com.bm.autoirrigationsys.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.autoirrigationsys.model.PlotDetailsEB;


public interface PlotDetailsRepository extends JpaRepository<PlotDetailsEB, Long> {

}
