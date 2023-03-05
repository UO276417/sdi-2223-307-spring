package com.uniovi.sdi2223307spring.pageObjects;

import org.openqa.selenium.*;

public class PO_LoginView extends PO_NavView{

    static public void fillLoginForm(WebDriver driver, String dnip, String password) {
        WebElement dni = driver.findElement(By.name("username"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);
        WebElement passwd = driver.findElement(By.name("password"));
        passwd.click();
        passwd.clear();
        passwd.sendKeys(password);
        //Pulsar el boton de Alta.
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}
