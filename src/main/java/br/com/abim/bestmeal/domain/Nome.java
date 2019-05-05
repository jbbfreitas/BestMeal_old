package br.com.abim.bestmeal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Nome.
 */
@Entity
@Table(name = "nome")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Nome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "primeiro_nome", length = 20, nullable = false)
    private String primeiroNome;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "nome_meio", length = 30, nullable = false)
    private String nomeMeio;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "sobre_nome", length = 30, nullable = false)
    private String sobreNome;

    @Column(name = "saudacao")
    private String saudacao;

    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "titulo", length = 15, nullable = false)
    private String titulo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public Nome primeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
        return this;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getNomeMeio() {
        return nomeMeio;
    }

    public Nome nomeMeio(String nomeMeio) {
        this.nomeMeio = nomeMeio;
        return this;
    }

    public void setNomeMeio(String nomeMeio) {
        this.nomeMeio = nomeMeio;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public Nome sobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
        return this;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getSaudacao() {
        return saudacao;
    }

    public Nome saudacao(String saudacao) {
        this.saudacao = saudacao;
        return this;
    }

    public void setSaudacao(String saudacao) {
        this.saudacao = saudacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Nome titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        Nome nome = (Nome) o;
        if (nome.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nome.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Nome{" +
            "id=" + getId() +
            ", primeiroNome='" + getPrimeiroNome() + "'" +
            ", nomeMeio='" + getNomeMeio() + "'" +
            ", sobreNome='" + getSobreNome() + "'" +
            ", saudacao='" + getSaudacao() + "'" +
            ", titulo='" + getTitulo() + "'" +
            "}";
    }
}
