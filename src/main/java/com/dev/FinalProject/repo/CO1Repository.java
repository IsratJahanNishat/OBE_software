package com.dev.FinalProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.FinalProject.entity.CO1;



public interface CO1Repository extends JpaRepository<CO1,Long>{

	@Query(value="select total from co1", nativeQuery=true)
	List<Integer> getByTable();
}
