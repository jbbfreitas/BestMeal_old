package br.com.abim.bestmeal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import br.com.abim.bestmeal.domain.enumeration.TipoLogradouro;

/**
 * A Logradouro.
 */
@Entity
@Table(name = "logradouro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Logradouro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoLogradouro tipo;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLogradouro getTipo() {
        return tipo;
    }

    public Logradouro tipo(TipoLogradouro tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoLogradouro tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public Logradouro nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Logradouro logradouro = (Logradouro) o;
        if (logradouro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logradouro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Logradouro{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
