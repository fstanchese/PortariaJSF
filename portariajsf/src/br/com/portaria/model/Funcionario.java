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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.portaria.util.BaseEntity;

@Entity
@Table(name="funcionario")
@NamedQueries({
	@NamedQuery(name="Funcionario.listar",query="SELECT funcionario FROM Funcionario funcionario ORDER BY funcionario.nome"),		
	@NamedQuery(name="Funcionario.buscaPorId",query="SELECT funcionario FROM Funcionario funcionario WHERE funcionario.id=:id"),
	@NamedQuery(name="Funcionario.login",query="SELECT funcionario FROM Funcionario funcionario WHERE funcionario.login=:login AND funcionario.senha=:senha")
})
public class Funcionario implements Serializable,BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "funcionario_seq_id")
	@SequenceGenerator(name = "funcionario_seq_id", sequenceName = "funcionario_id", allocationSize = 1)
	@Column(name="id")
	private Long id;	
	
	@NotEmpty(message="O campo Nome é obrigatório.")
	@Size(max=45,message="Tamanho máximo 100 caracteres.")
	@Column(name="nome",length=100,nullable=false)
	private String nome;

	@Size(max=10,message="Tamanho máximo 10 caracteres.")
	@Column(name="login",length=10,nullable=true)
	private String login;

	@Column(name="senha",length=40,nullable=true)
	private String senha;
	
	@NotNull(message="O campo Funcao é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="funcao_id",nullable=false)
	private Funcao funcao;	

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
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return acao;
	}
	
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", funcao=" + funcao + ", login=" + login + ", senha="
				+ senha + "]";
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
			
}
