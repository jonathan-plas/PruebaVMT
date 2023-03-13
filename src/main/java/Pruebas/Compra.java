package Pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Compra {

    public static WebDriver driver;

    public static void formulario_compra (WebDriver driver , String usuario) throws InterruptedException ) {

        WebElement nombre = driver.findElement(By.id("first-name"));
        nombre.sendKeys(usuario);

        WebElement apellido = driver.findElement(By.id("last-name"));
        apellido.sendKeys("Prueba2");

        WebElement postal = driver.findElement(By.id("postal-code"));
        postal.sendKeys("12336");
        Thread.sleep(3000);

        WebElement btncontinua = driver.findElement(By.id("continue"));
        btncontinua.click();
        Thread.sleep(3000);

    }






}
