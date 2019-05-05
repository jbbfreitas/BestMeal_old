package br.com.abim.bestmeal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import br.com.abim.bestmeal.domain.enumeration.SituacaoCartao;

/**
 * A CartaoRecompensa.
 */
@Entity
@Table(name = "cartao_recompensa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CartaoRecompensa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numero")
    private String numero;

    @NotNull
    @Size(min = 5, max = 5)
    @Column(name = "validade", length = 5, nullable = false)
    private String validade;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private SituacaoCartao situacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public CartaoRecompensa numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValidade() {
        return validade;
    }

    public CartaoRecompensa validade(String validade) {
        this.validade = validade;
        return this;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public CartaoRecompensa pontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
        return this;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public SituacaoCartao getSituacao() {
        return situacao;
    }

    public CartaoRecompensa situacao(SituacaoCartao situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(SituacaoCartao situacao) {
        this.situacao = situacao;
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
        CartaoRecompensa cartaoRecompensa = (CartaoRecompensa) o;
        if (cartaoRecompensa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cartaoRecompensa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CartaoRecompensa{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", validade='" + getValidade() + "'" +
            ", pontuacao=" + getPontuacao() +
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}
