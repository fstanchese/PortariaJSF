package br.com.portaria.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.portaria.util.BaseEntity;

@Entity
@Table(name="marca")
@NamedQueries({
	@NamedQuery(name = "Marca.listar",query="select marca from Marca marca order by marca.descricao"),		
	@NamedQuery(name = "Marca.buscaPorId",query="select marca from Marca marca where marca.id=:id"),
	@NamedQuery(name = "Marca.listarModelos",query="select modelo from Modelo modelo JOIN FETCH modelo.marca marca where marca.id=:id order by marca.descricao,modelo.descricao"),		
})
public class Marca implements Serializable,BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "marca_seq_id")
	@SequenceGenerator(name = "marca_seq_id", sequenceName = "marca_id", allocationSize = 1)
	@Column(name="id")
	private Long id;	
	
	@NotEmpty(message="O campo Descrição é obrigatório.")
	@Size(max=45,message="Tamanho máximo 45 caracteres.")
	@Column(name="descricao",length=45,nullable=false,unique=true)
	private String descricao;	

	@OneToMany(mappedBy="marca",targetEntity = Modelo.class,fetch=FetchType.LAZY)
	private List<Modelo> modelos;

	transient String acao;  

	
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
	public List<Modelo> getModelos() {
		return modelos;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return acao;
	}
	
	@Override
	public String toString() {
		return "Marca [id=" + id + ", descricao=" + descricao + "]";
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
		Marca other = (Marca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
