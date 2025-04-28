package com.spatiallaser.backend.entity;

import org.locationtech.jts.geom.Polygon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "real_estate_zoning")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon geom;

    private String name;

    @Column(name = "mail_city")
    private String mailCity;

    @Column(name = "mail_zip")
    private String mailZip;

    private String mailadd;

    @Column(name = "parcelnumb")
    private String parcelNumber;

    private String path;

    @Column(name = "usedesc")
    private String useDescription;

    @Column(name = "zoning_sub")
    private String zoningSub;

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