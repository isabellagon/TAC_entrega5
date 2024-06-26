package br.edu.utfpr.api.model;


import java.util.List;

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
@Table(name = "tb_gateway")
@Data

public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gatewayid;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "gateway", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Dispositivo> dispositivos;

    @ManyToOne
    @JoinColumn(name="pessoa_id")
    private Pessoa pessoa;

    public Long getId() {
        return gatewayid;
    }

    public void setId(Long gatewayid) {
        this.gatewayid = gatewayid;
    }
}
