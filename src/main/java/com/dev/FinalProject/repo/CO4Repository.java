package com.dev.FinalProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.FinalProject.entity.CO4;

public interface CO4Repository extends JpaRepository<CO4,Long>{

	@Query(value="select total from co4", nativeQuery=true)
	List<Integer> getByTable();
}
