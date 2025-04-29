package com.spatiallaser.backend.entity.reading;

import org.locationtech.jts.geom.Polygon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "real_estate_zoning")
public class Property {

    @Id
    @NotNull()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon geom;

    @NotNull()
    private String name;

    @Column(name = "mail_city")
    private String mailCity;

    @Column(name = "mail_zip")
    private String mailZip;

    private String mailadd;

    @NotNull()
    @Column(name = "parcelnumb")
    private String parcelNumber;

    @NotNull()
    private String path;

    @NotNull()
    @Column(name = "usedesc")
    private String useDescription;

    @NotNull()
    @Column(name = "zoning_sub")
    private String zoningSub;

    @NotNull()
    @Column(name = "zoning_typ")
    private String zoningType;

    public Property() {
    }

    public Long getId() {
        return id;
    }

    public Polygon getGeom() {
        return geom;
    }

    public String getName() {
        return name;
    }

    public String getMailCity() {
        return mailCity;
    }

    public String getMailZip() {
        return mailZip;
    }

    public String getMailadd() {
        return mailadd;
    }

    public String getParcelNumber() {
        return parcelNumber;
    }

    public String getPath() {
        return path;
    }

    public String getUseDescription() {
        return useDescription;
    }

    public String getZoningSub() {
        return zoningSub;
    }

    public String getZoningType() {
        return zoningType;
    }

}