package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.CorDAO;
import br.com.portaria.model.Cor;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private Cor corCadastro;
	private CorDAO dao = new CorDAO();
	private List<Cor> cores;
	private List<Cor> coresFiltrados;

	public void salvar() {
		try {
			dao.salvar(this.corCadastro);
			this.corCadastro = new Cor();
			FacesUtil.adicionarMsgInfo("Cor cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar uma Cor: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.corCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar uma Cor: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.corCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir uma Cor: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.cores = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.corCadastro = dao.buscaPorId(this.id);
			} else {
				this.corCadastro = new Cor();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter uma Cor: " + e.getMessage());
		}
	}

	public void limpar() {
		this.corCadastro = new Cor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Cor getCorCadastro() {
		return corCadastro;
	}

	public void setCorCadastro(Cor corCadastro) {
		this.corCadastro = corCadastro;
	}

	public List<Cor> getCores() {
		return cores;
	}

	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}

	public List<Cor> getCoresFiltrados() {
		return coresFiltrados;
	}

	public void setCoresFiltrados(List<Cor> coresFiltrados) {
		this.coresFiltrados = coresFiltrados;
	}	
}
