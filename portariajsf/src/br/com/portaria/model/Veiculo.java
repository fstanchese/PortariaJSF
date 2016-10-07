package br.com.portaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.portaria.util.BaseEntity;

@Entity
@Table(name = "veiculo")
@NamedQueries({
		@NamedQuery(name = "Veiculo.listar", query = "select veiculo,morador from Veiculo veiculo JOIN FETCH veiculo.morador morador order by morador.nome,veiculo.placa"),
		@NamedQuery(name = "Veiculo.buscaPorId", query = "select veiculo from Veiculo veiculo where veiculo.id=:id"),
})
public class Veiculo implements Serializable,BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_seq_id")
	@SequenceGenerator(name = "veiculo_seq_id", sequenceName = "veiculo_id", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "O campo Placa é obrigatório.")
	@Column(name="placa", length=8 ,nullable=false)
	private String placa;

	@NotNull(message = "O campo Ano de Fabricação é obrigatório.")
	@Column(name="ano", length=4 ,nullable=false)
	@Min(value=1900)
	private Integer ano;
	
	@NotNull(message = "O campo Morador é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "morador_id")
	private Morador morador;
	
	
	@NotNull(message = "O campo Modelo é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "modelo_id")
	private Modelo modelo;
	
	
	@NotNull(message = "O campo Cor é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cor_id")
	private Cor cor;

	transient String acao;  

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getAno() {
		return ano;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return acao;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", morador=" + morador + ", modelo=" + modelo + ", cor=" + cor
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
