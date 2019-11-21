@testeExemploAPI
Feature: Simulação e Contratação no APP BrasilPrev 
#Background: 

@testeExemploAPI  
Scenario: Pegar token no login 
	Given carrego a URL de login
	When envio o comando POST
	Then o token é do usuario é gerado
	
	
	