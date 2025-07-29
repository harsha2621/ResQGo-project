package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entities.TripStatusUpdate;

@Repository
public interface TripStatusUpdateDao extends JpaRepository<TripStatusUpdate, Long>{

}