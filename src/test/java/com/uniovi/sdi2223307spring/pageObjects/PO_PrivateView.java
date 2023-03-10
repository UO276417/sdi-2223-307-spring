package com.uniovi.sdi2223307spring.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.uniovi.sdi2223307spring.util.*;
import org.openqa.selenium.*;

import java.sql.*;
import java.util.*;

public class PO_PrivateView extends PO_NavView{
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
        //Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
        //Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    static public void login(WebDriver driver, String user, String password, String checktest){
        //Vamos al formulario de login.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, user, password);
        //Cmmprobamos que entramos en la pagina privada del Profesor
        PO_View.checkElementBy(driver, "text", checktest);

    }

    static public void menu(WebDriver driver, String text, String text2){
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, '"+ text +"')]/a");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, '"+ text2 +"')]");
        //Pinchamos en agregar Nota.
        elements.get(0).click();
    }
}


