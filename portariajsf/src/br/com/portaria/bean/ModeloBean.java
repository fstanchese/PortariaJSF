package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.ModeloDAO;
import br.com.portaria.model.Marca;
import br.com.portaria.model.Modelo;
import br.com.portaria.dao.MarcaDAO;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ModeloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private Modelo modeloCadastro;
	private ModeloDAO dao = new ModeloDAO();
	private List<Modelo> modelos;
	private List<Modelo> modelosFiltrados;
	private List<Marca> marcas;

	public void salvar() {
		try {
			dao.salvar(this.modeloCadastro);
			this.modeloCadastro = new Modelo();
			FacesUtil.adicionarMsgInfo("Modelo cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Modelo: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.modeloCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Modelo: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.modeloCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Modelo: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.modelos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			this.marcas = marcaDAO.listar();
			if (this.id != null) {
				this.modeloCadastro = dao.buscaPorId(this.id);
			} else {
				this.modeloCadastro = new Modelo();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Modelo: " + e.getMessage());
		}
	}

	public void limpar() {
		this.modeloCadastro = new Modelo();
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

	public Modelo getModeloCadastro() {
		return modeloCadastro;
	}

	public void setModeloCadastro(Modelo modeloCadastro) {
		this.modeloCadastro = modeloCadastro;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Modelo> getModelosFiltrados() {
		return modelosFiltrados;
	}

	public void setModelosFiltrados(List<Modelo> modelosFiltrados) {
		this.modelosFiltrados = modelosFiltrados;
	}	
	public List<Marca> getMarcas() {
		return marcas;
	}
	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
}
