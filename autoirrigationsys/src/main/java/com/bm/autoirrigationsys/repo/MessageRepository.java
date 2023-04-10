package com.bm.autoirrigationsys.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.autoirrigationsys.model.MessageEB;

public interface MessageRepository extends JpaRepository<MessageEB, Integer> {

}
