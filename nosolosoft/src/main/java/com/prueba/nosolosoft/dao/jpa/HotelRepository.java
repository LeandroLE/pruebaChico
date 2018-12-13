package com.prueba.nosolosoft.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.prueba.nosolosoft.entities.Hotel;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {
    Page findAll(Pageable pageable);
}