package me.looks.farmersapp.service;

import me.looks.farmersapp.dao.model.Region;
import me.looks.farmersapp.dao.repo.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public ResponseEntity<List<Region>> getAllRegions(String name, Long code) {

        List<Region> regions = regionRepository.findByArchivedFalse();

        if (name != null) {
            regions.removeIf(region -> !region.getName().equalsIgnoreCase(name));
        }
        if (code != null) {
            regions.removeIf(region -> !Objects.equals(region.getCode(), code));
        }

        return ResponseEntity.ok(regions);
    }

    public ResponseEntity<Region> createRegion(Region region) {
        Region savedEntity = regionRepository.save(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    public ResponseEntity<Region> updateRegion(Long id, Region updateRegion) {
        if (!regionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updateRegion.setId(id);

        Region savedEntity = regionRepository.save(updateRegion);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<Void> archiveRegion(Long id) {
        Region region = regionRepository.findById(id).orElse(null);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }

        region.setArchived(true);

        regionRepository.save(region);
        return ResponseEntity.ok().build();
    }
}
