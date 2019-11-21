package br.com.automation.Exemplo_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;
import org.junit.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import br.com.automation.Utils.ConnectionDB;
import io.restassured.RestAssured;
import io.restassured.response.Response;;


public class Brasilseg{
	
	public static Response restAssuredResponse;
	public static String guardaRequest;
	public String jsonFinalTest;
	@SuppressWarnings("unused")
	private ConnectionDB connectiondb = new ConnectionDB();
	
	@Test
    public void EmitirPropostaTest ()
    {
	
		//loga no end-point
		RestAssured.baseURI = "https://apigateway.dev-cld.brasilseg.com.br/api/emissao/subscricao/gerar_proposta";
		io.restassured.specification.RequestSpecification requestspecs = given();
		requestspecs.log();
		requestspecs.contentType("application/json");
		requestspecs.header("Authorization", "123");
		requestspecs.relaxedHTTPSValidation();
		
		//envia POST
		try {
//			ConnectionDB.main();
		String json = ("{ \r\n" + 
				"  \"localizadorCotacao\": \"0c29b335-27ff-4958-8940-66910fdbf7d6\"\r\n" + 
				"}");
		System.out.println("LOG: O request será: " + json);
		guardaRequest = json; 
		restAssuredResponse = requestspecs.body(json).post(RestAssured.baseURI);
		System.out.println("LOG: Response retornado é: " + restAssuredResponse.asString());
		jsonFinalTest = restAssuredResponse.asString();

		DocumentContext parsedJson = JsonPath.parse(restAssuredResponse.getBody().asString());
		
		String teste = parsedJson.jsonString();
		
		//valida Response
		restAssuredResponse.then().assertThat().body("status", equalTo("LOGGED_IN"));
		restAssuredResponse.then().assertThat().body("data.flagRegistroAtualizado", equalTo(true));
		
		System.out.println(teste);
    } catch (Exception e) {
		if (e.getMessage().contains("PROBLEMA NA SIMULAÇÃO!")) {
			e.printStackTrace();
			Assert.fail("Provavelmente o serviço está fora do ar.");
		} else {
			e.printStackTrace();
			Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. \n Response:"
					+ restAssuredResponse.asString() + "\n" + " Response: "
					+ restAssuredResponse.prettyPrint());
		}
	}
    }
}
