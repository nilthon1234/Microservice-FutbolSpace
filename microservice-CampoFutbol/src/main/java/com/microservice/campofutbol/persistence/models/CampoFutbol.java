package com.microservice.campofutbol.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "campo_futbol")
public class CampoFutbol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dni_use")
    private long dniEntity;
    @Column(name = "address")
    private  String addressEntity;
    @Column(name = "description")
    private String descriptionEntity;
    @Column(name = "city")
    private String cityEntity;
    @Column(name = "province")
    private String provinceEntity;
    @Column(name = "district")
    private String districtEntity;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estadoEntity = Estado.HABILITADO;
    public enum Estado {
            HABILITADO,
            DESABILITADO
    }

}
