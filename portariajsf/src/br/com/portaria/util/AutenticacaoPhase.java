package br.com.portaria.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.portaria.bean.AutenticarBean;
import br.com.portaria.model.Funcionario;

public class AutenticacaoPhase implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();

		boolean ehPaginaAutenticacao1 = paginaAtual.contains("autenticar.xhtml");
		boolean ehPaginaAutenticacao2 = paginaAtual.contains("principal.xhtml");

		if (!ehPaginaAutenticacao1 && !ehPaginaAutenticacao2) {

			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap();
			AutenticarBean autenticarBean = (AutenticarBean) mapa.get("autenticarBean");
			if (autenticarBean != null) {
				Funcionario funcionario = autenticarBean.getFuncionarioLogado();
				if (funcionario.getFuncao() == null) {
					FacesUtil.adicionarMsgErro("Acesso negado, faça login primeiro !!");
					Application application = facesContext.getApplication();
					NavigationHandler navigationHandler = application.getNavigationHandler();
					navigationHandler.handleNavigation(facesContext, null, "/pages/autenticar.xhtml?faces-redirect=true");
				}
			}
		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
