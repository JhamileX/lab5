/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.com470.boleto.app.service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.com470.boleto.app.dao.BoletoDao;
import com.com470.boleto.app.entities.Boleto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author jhamil
 */
public class BoletoServiceTest {
    
    private static Boleto boleto = new Boleto();
    private static int ID = 1;
    private static String EMAIL = "simon.siles@gmail.com";

    ArrayList<Boleto> iterable = new ArrayList<Boleto>();

    @Mock
    private static BoletoDao boletoDao;

    @InjectMocks
    private static BoletoService boletoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public BoletoServiceTest() {
    }

    @Test
    public void createBoletoTest() {
        final Boleto boleto = new Boleto();

        boleto.setBoletoId(1);

        boleto.setNombreDelPasajero("simon siles");
        boleto.setSalida("Sucre");
        boleto.setDestino("Potosi");
        boleto.setFecha(new Date());
        boleto.setEmail("simon.siles@gmail.com");
        Mockito.when(boletoDao.save(boleto)).thenReturn(boleto);
        Boleto response = boletoService.createBoleto(boleto);
        assertEquals(response, boleto);
    }

    @Test
    public void getBoletoByIdTest() {

        final Boleto boleto = new Boleto();
        boleto.setBoletoId(1);
        boleto.setNombreDelPasajero("simon siles");
        boleto.setSalida("Sucre");
        boleto.setDestino("Potosi");
        boleto.setFecha(new Date());
        boleto.setEmail("simon.siles@gmail.com");
        Mockito.when(boletoDao.findOne(ID)).thenReturn(boleto);
        Boleto response = boletoService.getBoletoById(1);
        assertEquals(response, boleto);
        assertEquals(response.getBoletoId(), boleto.getBoletoId());
        assertEquals(response.getFecha(), boleto.getFecha());
        assertEquals(response.getNombreDelPasajero(), boleto.getNombreDelPasajero());
        assertEquals(response.getEmail(), boleto.getEmail());
        assertEquals(response.getSalida(), boleto.getSalida());
        assertEquals(response.getDestino(), boleto.getDestino());

    }

    @Test
    public void updateBoletoTest() {

        Mockito.when(boletoDao.findOne(ID)).thenReturn(boleto);
        Boleto response = boletoService.updateBoleto(ID, EMAIL);
        assertEquals(response, boletoDao.save(boleto));
    }

    @Test
    public void getAllBoletoTest() {
        Mockito.when(boletoDao.findAll()).thenReturn(iterable);
    }

    @Test
    public void deleteBoletoTest() {
        boletoService.deleteBoleto(ID);
        Mockito.verify(boletoDao, Mockito.times(1)).delete(ID);
    }
    
}
