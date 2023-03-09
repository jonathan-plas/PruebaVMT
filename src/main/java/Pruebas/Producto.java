package Pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producto {

    public static WebDriver driver;
    //inventory_list


    public static void nose (WebDriver driver , String user , String password) throws InterruptedException {

        //Usuario
        WebElement impuser = driver.findElement(By.id("user-name"));
        impuser.sendKeys(user);
        //Contraseña
        WebElement imppass = driver.findElement(By.id("password"));
        imppass.sendKeys(password);
        //Botón ingresar
        WebElement btningresar = driver.findElement(By.id("login-button"));
        btningresar.click();

        Thread.sleep(2000);

    }

    public static WebElement wait_time_clickable (String soul) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (120));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(soul)));
    }

}