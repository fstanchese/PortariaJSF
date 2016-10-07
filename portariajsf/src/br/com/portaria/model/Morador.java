package br.com.portaria.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.portaria.util.BaseEntity;

@Entity
@Table(name = "morador")
@NamedQueries({
		@NamedQuery(name = "Morador.listar", query = "select morador from Morador morador order by morador.nome"),
		@NamedQuery(name = "Morador.buscaPorId", query = "select morador from Morador morador where morador.id=:id") 
})
public class Morador implements Serializable,BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "morador_seq_id")
	@SequenceGenerator(name = "morador_seq_id", sequenceName = "morador_id", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "O campo Nome é obrigatório.")
	@Size(max = 100, message = "Tamanho máximo 100 caracteres.")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name="cpf",length = 15, nullable = true)
	private String cpf;
	
	@Column(name="documento",length = 20, nullable = true)
	private String documento;

	@Column(name="celular",length = 15, nullable = true)
	private String celular;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="datanascto", nullable = true)
	private Date datanascto;
	
	@NotNull(message="O campo Unidade é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unidade_id",nullable=false)
	private Unidade unidade;

	@OneToMany(mappedBy = "morador",targetEntity = Veiculo.class)
	private List<Veiculo> veiculos;

	transient String acao;  

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getDatanascto() {
		return datanascto;
	}

	public void setDatanascto(Date datanascto) {
		this.datanascto = datanascto;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return acao;
	}	
	@Override
	public String toString() {
		return "Morador [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", documento=" + documento + ", celular="
				+ celular + ", datanascto=" + datanascto + ", unidade=" + unidade + "]";
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
		Morador other = (Morador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
