package me.looks.farmersapp.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "legal_form")
    private String legalForm;

    @Column(name = "inn", nullable = false)
    private String inn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "ogrn")
    private String ogrn;

    @ManyToOne
    @JoinColumn(name = "registration_region_id", nullable = false)
    private Region registrationRegion;

    @ManyToMany
    @JoinTable(
            name = "crop_field_regions",
            joinColumns = @JoinColumn(name = "farmer_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id")
    )
    private List<Region> cropFieldRegions;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "archived")
    private Boolean archived;


}
