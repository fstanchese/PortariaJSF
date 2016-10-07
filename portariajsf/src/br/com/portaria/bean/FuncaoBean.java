package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.FuncaoDAO;
import br.com.portaria.model.Funcao;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcao funcaoCadastro;
	private List<Funcao> funcoes;
	private List<Funcao> funcoesFiltrados;
	private FuncaoDAO dao = new FuncaoDAO();
	private Long id;
	private String acao;

	public void salvar() {
		try {
			dao.salvar(this.funcaoCadastro);
			this.funcaoCadastro = new Funcao();
			FacesUtil.adicionarMsgInfo("Função cadastrada com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar uma Função: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.funcaoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar uma Função: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.funcaoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir uma Função: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.funcoes = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.funcaoCadastro = dao.buscaPorId(this.id);
			} else {
				this.funcaoCadastro = new Funcao();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter uma Função: " + e.getMessage());
		}
	}

	public void limpar() {
		this.funcaoCadastro = new Funcao();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Funcao getFuncaoCadastro() {
		return funcaoCadastro;
	}

	public void setFuncaoCadastro(Funcao funcaoCadastro) {
		this.funcaoCadastro = funcaoCadastro;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public List<Funcao> getFuncoes() {
		return this.funcoes;
	}

	public List<Funcao> getFuncoesFiltrados() {
		return funcoesFiltrados;
	}

	public void setFuncoesFiltrados(List<Funcao> funcoesFiltrados) {
		this.funcoesFiltrados = funcoesFiltrados;
	}
}
