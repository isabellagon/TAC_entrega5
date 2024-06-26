package br.edu.utfpr.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sensor")
@Data

public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sensorid;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Leitura> leituras;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    @JsonBackReference
    private Dispositivo dispositivo;
}
