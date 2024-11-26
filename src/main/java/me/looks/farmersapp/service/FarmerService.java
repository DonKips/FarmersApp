package me.looks.farmersapp.service;

import me.looks.farmersapp.dao.dto.FarmerDTO;
import me.looks.farmersapp.dao.model.Farmer;
import me.looks.farmersapp.dao.model.Region;
import me.looks.farmersapp.dao.repo.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FarmerService {
    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public ResponseEntity<List<Farmer>> getAllFarmers(String name,
                                                      String inn,
                                                      Boolean archived,
                                                      Long registrationRegionId) {
        List<Farmer> farmers = farmerRepository.findAll();

        if (name != null) {
            farmers.removeIf(farmer -> !farmer.getName().equalsIgnoreCase(name));
        }
        if (inn != null) {
            farmers.removeIf(farmer -> !farmer.getInn().equalsIgnoreCase(inn));
        }
        if (archived != null) {
            farmers.removeIf(farmer -> farmer.getArchived() != archived);
        }
        if (registrationRegionId != null) {
            farmers.removeIf(farmer -> !Objects.equals(farmer.getRegistrationRegion().getId(), registrationRegionId));
        }

        return ResponseEntity.ok(farmers);
    }

    public ResponseEntity<FarmerDTO> getFarmerById(Long id) {
        Optional<Farmer> optionalFarmer = farmerRepository.findById(id);

        if (optionalFarmer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farmer farmer = optionalFarmer.get();

        FarmerDTO farmerDTO = new FarmerDTO(
                farmer.getId(),
                farmer.getName(),
                farmer.getLegalForm(),
                farmer.getInn(),
                farmer.getKpp(),
                farmer.getOgrn(),
                farmer.getRegistrationRegion().getId(),
                farmer.getCropFieldRegions().stream().map(Region::getName).toList()
        );

        return ResponseEntity.ok(farmerDTO);

    }

    public ResponseEntity<Farmer> createFarmer(Farmer farmer) {
        Farmer savedEntity = farmerRepository.save(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    public ResponseEntity<Farmer> updateFarmer(Long id, Farmer updateFarmer) {
        if (!farmerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updateFarmer.setId(id);

        Farmer savedEntity = farmerRepository.save(updateFarmer);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<Void> archiveFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id).orElse(null);
        if (farmer == null) {
            return ResponseEntity.notFound().build();
        }

        farmer.setArchived(true);

        farmerRepository.save(farmer);
        return ResponseEntity.ok().build();
    }

}
