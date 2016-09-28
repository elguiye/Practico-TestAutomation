package compraPasajes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class CompraTest {
	
	@Test
	public void openPage(){
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.centraldepasajes.com.ar/");
		MainPage compra = new MainPage(driver);
		
		//Elegimos origen
		compra.setInputsOrigen();
		
		//Eleginos Destino
		compra.setInputsDestino();
		
		//Elegimos fecha de partida
		compra.setCalendarInput();
		
		//Ir a buscar
		compra.buscaPasajes();
		
		//Verifica pagina de servicio
		compra.VerificaPaginaServicios();
		
	}
	

}
