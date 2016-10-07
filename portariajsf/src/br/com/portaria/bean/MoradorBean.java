package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.MoradorDAO;
import br.com.portaria.dao.UnidadeDAO;
import br.com.portaria.model.Morador;
import br.com.portaria.model.Unidade;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MoradorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Morador moradorCadastro;
	private List<Morador> moradores;
	private List<Morador> moradoresFiltrados;
	private MoradorDAO dao = new MoradorDAO();
	private Long id;
	private String acao;
	private List<Unidade> unidades;
	
	public void salvar() {
		try {
			dao.salvar(this.moradorCadastro);
			this.moradorCadastro = new Morador();
			FacesUtil.adicionarMsgInfo("Morador cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Morador: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.moradorCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Morador: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.moradorCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Morador: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.moradores = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}

	public void carregarDados() {
		try {
			
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidades = unidadeDAO.listar();
			if (this.id != null) {
				this.moradorCadastro = dao.buscaPorId(this.id);
			} else {
				this.moradorCadastro = new Morador();
			}

		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Morador: " + e.getMessage());
		}
	}

	public void limpar() {
		this.moradorCadastro = new Morador();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getAcao() {
		return this.acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Morador getMoradorCadastro() {
		return moradorCadastro;
	}

	public void setMoradorCadastro(Morador moradorCadastro) {
		this.moradorCadastro = moradorCadastro;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public List<Morador> getMoradores() {
		return this.moradores;
	}

	public List<Morador> getMoradoresFiltrados() {
		return this.moradoresFiltrados;
	}

	public void setMoradoresFiltrados(List<Morador> moradoresFiltrados) {
		this.moradoresFiltrados = moradoresFiltrados;
	}
	
	public List<Unidade> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
}
