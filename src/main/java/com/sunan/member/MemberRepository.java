package com.sunan.member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Member;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Integer> {

	public Optional<Member> findById(int id);

	public Page<Member> findByNameContainingIgnoreCaseAndIsActive(String name, String active, Pageable pageable);

	public Page<Member> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Member SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
