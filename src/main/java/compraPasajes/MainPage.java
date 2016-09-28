package compraPasajes;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage {
	
		public WebDriver driver;
		private String origen ="Corrientes Terminal";
		private String destino ="Cordoba Terminal";
		private String TitlePageService = "Selección de servicios";
		private String TitlePageButacas = "Selección de butacas";
		private String AlertPageButaca = "Tenés que seleccionar al menos una butaca para continuar";
		private WebElement inputOrigen;
		private WebElement inputDestino;
		private WebElement imgCalendar;
		private WebElement botonBuscar;
		
		public MainPage(WebDriver driver){
			this.driver = driver;
			this.inputOrigen = this.driver.findElement(By.cssSelector("#PadOrigen"));
			this.inputDestino = this.driver.findElement(By.cssSelector("#PadDestino"));
			this.imgCalendar = this.driver.findElement(By.xpath("//*[@id='busq-left']/img"));
			
		}
		
		public void setInputsOrigen(){
			this.inputOrigen.sendKeys(this.origen);
			new WebDriverWait(this.driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[1]")));
			this.inputOrigen.sendKeys(Keys.ARROW_DOWN);
			this.inputOrigen.sendKeys(Keys.TAB);
		}																						
			
		public void setInputsDestino(){
			
			this.inputDestino.sendKeys(this.destino);
			new WebDriverWait(this.driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[2]")));	
			this.inputDestino.sendKeys(Keys.ARROW_DOWN);
			this.inputDestino.sendKeys(Keys.TAB);
			
		}
		
		
		public void setCalendarInput(){
			this.imgCalendar.click();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 8);
			
		    String dia = Integer.toString(calendar.get(Calendar.DATE));
		    String  mes = Integer.toString(calendar.get(Calendar.MONTH));
		    String annio = Integer.toString(calendar.get(Calendar.YEAR));
	
		    WebElement fechaPartida = this.driver.findElement(By.xpath(".//td[contains(@onclick, '"+mes+","+annio+"')]/a[text()='"+dia+"']"));
			fechaPartida.click();
		}
		
		public void buscaPasajes(){
			
			this.botonBuscar = this.driver.findElement(By.xpath(".//*[@id='btnCons']"));
			this.botonBuscar.click();
			
		}
		
		public void VerificaPaginaServicios(){
				
			//Encabezado seccion servicios
			WebElement pageService = this.driver.findElement(By.xpath(".//*[@id='Heading1']"));
			Assert.assertEquals(pageService.getText().toString(), ""+this.TitlePageService+"", "Deberiamos estar en la pagina de servicios");

			//Encabezado origen y destino 
			WebElement headTable = this.driver.findElement(By.xpath(".//*[@id='lblCabecera']"));
			Assert.assertEquals(headTable.getText().toString(), "IDA "+this.origen+" a "+this.destino+"", "El origen y destino no son correctos");
			

			//Click para seleccion un boleto
			WebElement btnComprar = this.driver.findElement(By.xpath(".//*[@id='grillaservicios_ctl02_lkbComprar']/strong"));
			
			btnComprar.click();
			
			//Encabezado seccion butacas
			WebElement pageButacas = this.driver.findElement(By.xpath(".//*[@id='Heading1']"));
			Assert.assertEquals(pageButacas.getText().toString(), ""+this.TitlePageButacas+"", "Deberiamos estar en la pagina de seccion butacas");
			
			//Boton siguiente en seccion butacas
			WebElement btnContinuar = this.driver.findElement(By.xpath(".//*[@id='btnSigue']"));
			
			btnContinuar.click();
			
			//mensaje de no seleccion de butacas
			WebElement msgAlert = this.driver.findElement(By.xpath(".//*[@id='Alertbar1_labelAlerta']"));
				
			Assert.assertEquals(msgAlert.getText().toString(), ""+this.AlertPageButaca+"", "Alerta no es correcta ");
			
		}
				
}
