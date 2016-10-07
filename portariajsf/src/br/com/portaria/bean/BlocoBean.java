package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.BlocoDAO;
import br.com.portaria.model.Bloco;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class BlocoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private Bloco blocoCadastro;
	private BlocoDAO dao = new BlocoDAO();
	private List<Bloco> blocos;
	private List<Bloco> blocosFiltrados;

	public void salvar() {
		try {
			dao.salvar(this.blocoCadastro);
			this.blocoCadastro = new Bloco();
			FacesUtil.adicionarMsgInfo("Bloco cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Bloco: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.blocoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Bloco: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.blocoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Bloco: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.blocos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.blocoCadastro = dao.buscaPorId(this.id);
			} else {
				this.blocoCadastro = new Bloco();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Bloco: " + e.getMessage());
		}
	}

	public void limpar() {
		this.blocoCadastro = new Bloco();
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

	public Bloco getBlocoCadastro() {
		return blocoCadastro;
	}

	public void setBlocoCadastro(Bloco blocoCadastro) {
		this.blocoCadastro = blocoCadastro;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public List<Bloco> getBlocosFiltrados() {
		return blocosFiltrados;
	}

	public void setBlocosFiltrados(List<Bloco> blocosFiltrados) {
		this.blocosFiltrados = blocosFiltrados;
	}	
}
