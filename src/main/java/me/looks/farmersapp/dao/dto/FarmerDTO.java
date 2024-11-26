package me.looks.farmersapp.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FarmerDTO {

    private Long id;

    private String name;

    private String legalForm;

    private String inn;

    private String kpp;

    private String ogrn;

    private Long registrationRegion;

    private List<String> cropFieldRegions;

    private Date registrationDate;

    private Boolean archived;

    public FarmerDTO(Long id, String name, String legalForm, String inn, String kpp,
                     String ogrn, Long registrationRegion, List<String> cropFieldRegions) {
        this.id = id;
        this.name = name;
        this.legalForm = legalForm;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.registrationRegion = registrationRegion;
        this.cropFieldRegions = cropFieldRegions;
    }

}
