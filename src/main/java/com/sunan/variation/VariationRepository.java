package com.sunan.variation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Hotel;
import com.sunan.model.Variation;

@Repository
public interface VariationRepository extends PagingAndSortingRepository<Variation, Integer> {

	public Optional<Variation> findById(int id);


	public Page<Variation> findByStatusAndHotel(String active, Pageable pageable,Hotel hotel);

	

}
