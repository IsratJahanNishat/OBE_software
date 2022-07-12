package com.dev.FinalProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.FinalProject.entity.CO5;

public interface CO5Repository extends JpaRepository<CO5,Long>{
	@Query(value="select total from co5", nativeQuery=true)
	List<Integer> getByTable();
}
