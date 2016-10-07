package br.com.portaria.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.portaria.util.BaseEntity;

@Entity
@Table(name = "unidade")
@NamedQueries({
		@NamedQuery(name = "Unidade.listar", query = "select unidade from Unidade unidade order by unidade.descricao"),
		@NamedQuery(name = "Unidade.buscaPorId", query = "select unidade from Unidade unidade where unidade.id=:id") 
})
public class Unidade implements Serializable,BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_seq_id")
	@SequenceGenerator(name = "unidade_seq_id", sequenceName = "unidade_id", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "O campo Descriçãoo é obrigatório.")
	@Size(max = 45, message = "Tamanho máximo 45 caracteres.")
	@Column(name = "descricao", length = 45, nullable = false, unique = true)
	private String descricao;

	@Size(max = 8, message = "Tamanho máximo 8 digitos.")
	@Column(name="ramal", length=8,nullable=true)
	private String ramal;
	
	@Size(max = 15, message = "Tamanho máximo 15 caracteres.")
	@Column(name="telefone", length=15,nullable=true)
	private String telefone;

	@Column(name="vagas", length=1 ,nullable=true)
	private int vagas;
	
	transient String acao;  
	
	@NotNull(message = "O campo Tipo de Morador é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "moradortipo_id")
	private MoradorTipo moradorTipo;

	
	@NotNull(message = "O campo Tipo de Unidade é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unidadetipo_id")
	private UnidadeTipo unidadeTipo;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "bloco_id")
	private Bloco bloco;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "andar_id")
	private Andar andar;

	
	@OneToMany(mappedBy = "unidade",targetEntity = Morador.class)
	private List<Morador> moradores;

	@OneToMany(mappedBy = "unidade",targetEntity = Visitante.class)
	private List<Visitante> visitantes;	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getRamal() {
		return this.ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	public UnidadeTipo getUnidadeTipo() {
		return unidadeTipo;
	}
	public void setUnidadeTipo(UnidadeTipo unidadeTipo) {
		this.unidadeTipo = unidadeTipo;
	}
	public MoradorTipo getMoradorTipo() {
		return moradorTipo;
	}
	public void setMoradorTipo(MoradorTipo moradorTipo) {
		this.moradorTipo = moradorTipo;
	}
	public Bloco getBloco() {
		return bloco;
	}
	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	public Andar getAndar() {
		return andar;
	}
	public void setAndar(Andar andar) {
		this.andar = andar;
	}
	public List<Morador> getMoradores() {
		return moradores;
	}
	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}
	public void setVisitantes(List<Visitante> visitantes) {
		this.visitantes = visitantes;
	}
	public List<Visitante> getVisitantes() {
		return visitantes;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return acao;
	}
	@Override
	public String toString() {
		return "Unidade [id=" + id + ", descricao=" + descricao + ", ramal=" + ramal + ", telefone=" + telefone
				+ ", vagas=" + vagas + ", moradorTipo=" + moradorTipo + ", unidadeTipo=" + unidadeTipo + ", bloco="
				+ bloco + ", andar=" + andar + "]";
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
		Unidade other = (Unidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
