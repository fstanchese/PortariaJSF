package br.com.portaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.portaria.dao.FuncionarioDAO;
import br.com.portaria.model.Funcionario;
import br.com.portaria.util.FacesUtil;

@ManagedBean(name="autenticarBean")
@SessionScoped
public class AutenticarBean {
	private Funcionario funcionarioLogado;
	
	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public Funcionario getFuncionarioLogado() {
		if (this.funcionarioLogado == null) {
			this.funcionarioLogado = new Funcionario();
		}
		return this.funcionarioLogado;
	}
	
	public String entrar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			this.funcionarioLogado = dao.autenticar(funcionarioLogado.getLogin(), DigestUtils.md5Hex( funcionarioLogado.getSenha()));
			if (this.funcionarioLogado==null) {
				FacesUtil.adicionarMsgErro("Login ou senha incorreto");
				return null;
			} else {
				FacesUtil.adicionarMsgInfo("Login efetuado com sucesso");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar autenticar no sistema"+e.getMessage());
			return null;
		}
	}
	
	public String sair() {
		this.funcionarioLogado = null;
		FacesUtil.adicionarMsgInfo("Logoff efetuado com sucesso");
		return "/pages/principal.xhtml?faces-redirect=true";
	}

	@Override
	public String toString() {
		return "AutenticarBean [funcionarioLogado=" + funcionarioLogado + "]";
	}
	
}
