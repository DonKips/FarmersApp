package me.looks.farmersapp.dao.repo;

import me.looks.farmersapp.dao.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {

    List<Region> findByArchivedFalse();

}
