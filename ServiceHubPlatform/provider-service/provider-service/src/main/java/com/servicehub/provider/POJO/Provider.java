package com.servicehub.provider.POJO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "providers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authUserId")
    private Long authUserId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "experience", nullable = false)
    private Integer experienceYears;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "totalJobs", nullable = false)
    private Integer totalJobs;

    @Column(name = "serviceRadius", nullable = false)
    private Double serviceRadiusKm;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "verification", nullable = false)
    private Boolean verified;

    private Double latitude;
    private Double longitude;
}
