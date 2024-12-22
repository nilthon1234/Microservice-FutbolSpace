package com.microservice.campofutbol.persistence.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "El dni no puede ser nulo")
    @Min(value = 10000000, message = "el dni debe ser menor a 8 digitos")
    @Column(name = "dni_use")
    private long dniEntity;

    @NotBlank(message = "el addressEntity no puede estar vacia")
    @Size(max = 255, message = "la direcction  no puede exceder los 255 caracteres")
    @Column(name = "address")
    private  String addressEntity;

    @NotBlank(message = "el descriptionEntity no puede estar vacia ")
    @Size(max = 500, message = "la descriptio no puede exceder los 500 caracteres")
    @Column(name = "description")
    private String descriptionEntity;

    @NotBlank(message = "el city no puede estar vacia")
    @Size(max = 50, message = "el city no puede exceder lo 50 caracteres")
    @Column(name = "city")
    private String cityEntity;

    @NotBlank(message = "el province no puede estar  vacio")
    @Size(max = 50, message = "el province no puede exceder los 50 caracteres")
    @Column(name = "province")
    private String provinceEntity;

    @NotBlank(message = "el distric no puede estar vacio")
    @Size(max = 50, message = "el distric no puede exceder los 50 caracteres")
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
