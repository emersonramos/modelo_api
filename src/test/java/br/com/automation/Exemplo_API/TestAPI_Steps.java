package br.com.automation.Exemplo_API;

import br.com.automation.Pages.APIPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("deprecation")
public class TestAPI_Steps {

	private APIPage telaInicialPage = new APIPage();

	@Given("carrego a URL de login")
	public void carregar_URL_Login() {
		telaInicialPage.carregaAPI();
	}

	@When("envio o comando POST")
	public void envia_Post() throws InterruptedException {
		telaInicialPage.enviaPOST();
	}

	@Then("o token é do usuario é gerado")
	public void token_Validado() throws InterruptedException {
		telaInicialPage.validaResponse();
	}
}
