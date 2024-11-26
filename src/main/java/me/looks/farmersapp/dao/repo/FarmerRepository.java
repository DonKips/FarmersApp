package me.looks.farmersapp.dao.repo;

import me.looks.farmersapp.dao.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
