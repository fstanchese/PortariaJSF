package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.MarcaDAO;
import br.com.portaria.model.Marca;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private Marca marcaCadastro;
	private MarcaDAO dao = new MarcaDAO();
	private List<Marca> marcas;
	private List<Marca> marcasFiltrados;

	public void salvar() {
		try {
			dao.salvar(this.marcaCadastro);
			this.marcaCadastro = new Marca();
			FacesUtil.adicionarMsgInfo("Marca cadastrada com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar uma Marca: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.marcaCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar uma Marca: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.marcaCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir uma Marca: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.marcas = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.marcaCadastro = dao.buscaPorId(this.id);
			} else {
				this.marcaCadastro = new Marca();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter uma Marca: " + e.getMessage());
		}
	}

	public void limpar() {
		this.marcaCadastro = new Marca();
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

	public Marca getMarcaCadastro() {
		return marcaCadastro;
	}

	public void setMarcaCadastro(Marca marcaCadastro) {
		this.marcaCadastro = marcaCadastro;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Marca> getMarcasFiltrados() {
		return marcasFiltrados;
	}

	public void setMarcasFiltrados(List<Marca> marcasFiltrados) {
		this.marcasFiltrados = marcasFiltrados;
	}
	
}
