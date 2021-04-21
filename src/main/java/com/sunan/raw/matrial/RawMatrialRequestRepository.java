package com.sunan.raw.matrial;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.RecipeRawMatrial;
@Repository
public interface RawMatrialRequestRepository extends PagingAndSortingRepository<RecipeRawMatrial, Integer> {

}
