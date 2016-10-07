package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.CorDAO;
import br.com.portaria.dao.MarcaDAO;
import br.com.portaria.dao.MoradorDAO;
import br.com.portaria.dao.VeiculoDAO;
import br.com.portaria.model.Cor;
import br.com.portaria.model.Marca;
import br.com.portaria.model.Modelo;
import br.com.portaria.model.Morador;
import br.com.portaria.model.Veiculo;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long marcaId;
	private String acao;
	private Veiculo veiculoCadastro;
	private VeiculoDAO dao = new VeiculoDAO();
	private List<Veiculo> veiculos;
	private List<Veiculo> veiculosFiltrados;
	private List<Marca> marcas;
	private List<Modelo> modelos;
	private List<Cor> cores;
	private List<Morador> moradores;

	public void salvar() {
		try {
			dao.salvar(this.veiculoCadastro);
			this.veiculoCadastro = new Veiculo();
			FacesUtil.adicionarMsgInfo("Veículo cadastrado com sucesso !");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Veículo : " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.veiculoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Veículo : " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.veiculoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Veículo : " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.veiculos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar solicitação : " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			CorDAO corDAO = new CorDAO();
			MoradorDAO moradorDAO = new MoradorDAO();
			MarcaDAO marcaDAO = new MarcaDAO();
			
			this.cores = corDAO.listar();
			this.moradores = moradorDAO.listar();
			this.marcas = marcaDAO.listar();
		
			if (this.id != null) {
				this.veiculoCadastro = dao.buscaPorId(this.id);
				if (this.marcaId == null) {
					this.marcaId = veiculoCadastro.getModelo().getMarca().getId();
				}
				this.modelos = marcaDAO.listarModelos(marcaId);		
			} else {
				this.modelos = marcaDAO.listarModelos(marcaId);		
				this.veiculoCadastro = new Veiculo();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Veículo : " + e.getMessage());
		}
	}
	
	public void limpar() {
		this.veiculoCadastro = new Veiculo();
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

	public Veiculo getVeiculoCadastro() {
		return veiculoCadastro;
	}

	public void setVeiculoCadastro(Veiculo veiculoCadastro) {
		this.veiculoCadastro = veiculoCadastro;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<Veiculo> getVeiculosFiltrados() {
		return veiculosFiltrados;
	}

	public void setVeiculosFiltrados(List<Veiculo> veiculosFiltrados) {
		this.veiculosFiltrados = veiculosFiltrados;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Cor> getCores() {
		return cores;
	}

	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

}
