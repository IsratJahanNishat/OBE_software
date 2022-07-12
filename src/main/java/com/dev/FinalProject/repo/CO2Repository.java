package com.dev.FinalProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.FinalProject.entity.CO2;


public interface CO2Repository extends JpaRepository<CO2,Long>{

	@Query(value="select total from co2", nativeQuery=true)
	List<Integer> getByTable();
}
