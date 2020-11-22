package com.vadas.MarsRoverApi.repository;

import com.vadas.MarsRoverApi.dto.HomeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepository extends JpaRepository<HomeDTO, Long> {

    HomeDTO findByUserId(Long userId);

}
