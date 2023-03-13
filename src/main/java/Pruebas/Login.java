package Pruebas;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Login {
    String url = "https://www.saucedemo.com/";
    public static WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("Webdriver.chrome.driver", "C:\\Users\\HP\\OneDrive\\Escritorio\\Todas las Carpetas\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }
    @Test
    public void Login() throws InterruptedException, IOException {
        String filePath = "C:\\Users\\DELL\\Desktop\\Login.xlsx";
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(1);
        Cell usernameCell = row.getCell(0);
        Cell passwordCell = row.getCell(1);
        String username = usernameCell.getStringCellValue();
        String password = passwordCell.getStringCellValue();


        Producto.nose(driver,username,password);



    //Ordenar de mator a menor 
        Select ordenar = new Select(driver.findElement(By.className("product_sort_container")));
        ordenar.selectByVisibleText("Price (high to low)");

        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        //Arreglo vacio
        List<WebElement> selectedProducts = new ArrayList<>();


        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = rand.nextInt(products.size());
            selectedProducts.add(products.get(randomIndex));
            products.remove(randomIndex);
        }

        for (WebElement product : selectedProducts) {
            String productName = product.findElement(By.className("inventory_item_name")).getText();
            System.out.println("Producto seleccionado: " + productName);
            product.click();
        }

        wait_time_clickable("//A[@class='shopping_cart_link']");
        //Carro
        WebElement carro = driver.findElement(By.xpath("//A[@class='shopping_cart_link']"));
        carro.click();
        WebElement btncontinuar = driver.findElement(By.id("checkout"));
        btncontinuar.click();
        Thread.sleep(3000);

        //Formulario de compra

        Compra.formulario_compra(driver,usuario);


        WebElement btnfinish = driver.findElement(By.id("finish"));
        btnfinish.click();
        Thread.sleep(3000);

        WebElement btnhome = driver.findElement(By.id("back-to-products"));
        btnhome.click();
        Thread.sleep(3000);
        WebElement menu = driver.findElement(By.xpath("//BUTTON[@id='react-burger-menu-btn']"));
        menu.click();
        Thread.sleep(2000);
        WebElement logout = driver.findElement(By.xpath("//A[@id='logout_sidebar_link']"));
        logout.click();
    }
    //Metodo para tener globalizado el tiempo de espera explicito
    public static WebElement wait_time_clickable (String soul) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (120));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(soul)));
    }
    @AfterMethod
    public void CerrarSesión(){
        driver.quit();
    }
}
