package br.com.automation.Exemplo_API;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/br/com/automation/features/teste.feature",glue = {""}, tags = {
"@testeExemploAPI" }, monochrome = true, dryRun = false)
public class Runner_TestAPI {
	

}
