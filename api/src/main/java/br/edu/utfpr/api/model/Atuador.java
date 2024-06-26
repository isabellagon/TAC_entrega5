package br.edu.utfpr.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_atuador")
@Data

public class Atuador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long atuadorid;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    @JsonBackReference
    private Dispositivo dispositivo;
}
