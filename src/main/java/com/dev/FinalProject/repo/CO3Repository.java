package com.dev.FinalProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.FinalProject.entity.CO3;

public interface CO3Repository extends JpaRepository<CO3,Long>{

	@Query(value="select total from co3", nativeQuery=true)
	List<Integer> getByTable();
}
