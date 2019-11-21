package br.com.automation.Pages;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIPage {

	public static String operacao = null;
	public static Object[] arrElement;
	public static Map<String, Object> payLoadGeral = new HashMap<String, Object>();
	static String cenarioFixo;
	public static Response restAssuredResponse;
	public static String guardaRequest;
	public String jsonFinalTest;
	public static Map<String, Object> valorBody;
	io.restassured.specification.RequestSpecification requestspecs = given();

	public void carregaAPI() {
		RestAssured.baseURI = "https://api-hml.brasilprev.com.br/v1/autenticacao/login";

		requestspecs.log();
		requestspecs.contentType("application/json");
		requestspecs.header("x-api-key", "KClztmXPjT59tTXbrLgce1bMd9n5jphq2Szv3AXM");
		requestspecs.header("Authorization",
				"bearer UUxXUk1iUFpQWGY2Mm9GczRHOFhtU0dVelg1dGhiMzI6TWRnS3ltSk1aR2tDejVvbktEcUVOSlByWXlLRVl1dFltNVlCc3NGRlJMVGJXVUtlU0RVckFxUkE0WnhKVWl1cg==");
		requestspecs.relaxedHTTPSValidation();
	}

	public void enviaPOST() {
		try {
			
			valorBody = montaBody(); 
			System.out.println(valorBody.toString());
			
			Gson g = new Gson();
			String json = g.toJson(valorBody);
			System.out.println("LOG: O request será: " + json);
			guardaRequest = json; 
			restAssuredResponse = requestspecs.body(json).post(RestAssured.baseURI);
			System.out.println("LOG: Response retornado é: " + restAssuredResponse.asString());
			jsonFinalTest = restAssuredResponse.asString();
			
		} catch (Exception e) {
			if (e.getMessage().contains("PROBLEMA NA SIMULAÇÃO!")) {
				e.printStackTrace();
				Assert.fail("Provavelmente o serviço está fora do ar.");
			} else {
				e.printStackTrace();
				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. \n Response:"
						+ restAssuredResponse.asString() + "\n" + " Response: " + restAssuredResponse.prettyPrint());
			}
		}
	}

	public void validaResponse() {
		// valida Response
		restAssuredResponse.then().assertThat().body("status", equalTo("LOGGED_IN"));
		restAssuredResponse.then().assertThat().body("data.flagRegistroAtualizado", equalTo(true));
	}

	public Map<String, Object> montaBody() {

		arrElement = new Object[] {"", "38995063831",
				"15151515"};
		
		Object[] arrFinal = (arrElement);
		System.out.println("Tamanho do array final: " + arrFinal.length);
		System.out.println("Tamanho do array final: " + arrElement.toString());

		assertTrue(arrFinal.length > 0);

		for (int g = 0; g < arrFinal.length; g++) {
			System.out.println("LOG: " + g + " _ " + arrFinal[g]);
		}
			    
		payLoadGeral.put("cpf", (arrFinal[1]));
		payLoadGeral.put("senha", (arrFinal[2]));
		
		System.out.println(payLoadGeral.values());	

		return payLoadGeral;
	}
	
	
}
