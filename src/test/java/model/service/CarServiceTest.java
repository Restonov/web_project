package model.service;

import static org.mockito.Mockito.*;

import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.CarDaoImpl;
import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.CarService;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class CarServiceTest extends Assert {
    CarService carService;
    Car expectedCar;
    Car mockCar;
    EntityTransaction transaction;
    CarDaoImpl dao;

    @BeforeClass
    public void setUp() {
        transaction = mock(EntityTransaction.class);
        dao = mock(CarDaoImpl.class);
        carService = new CarService(dao, transaction);

        expectedCar = new Car();
        expectedCar.setState(Car.State.AVAILABLE);
        expectedCar.setName("Mercedes S Class");
        expectedCar.setId(5);
        expectedCar.setDescriptionEng("car description");
        mockCar = mock(Car.class);
    }

    @AfterClass
    public void tearDown() {
        carService = null;
        expectedCar = null;
        transaction = null;
        dao = null;
        mockCar = null;
    }

    @Test
    public void defineDescriptionAndGetTest() {
        String expected = "car description";
        String actual = carService.defineDescriptionAndGet(expectedCar, AttributeName.EN).getDescription();
        AssertJUnit.assertEquals(expected, actual);
    }

    @Test
    public void findCarListTest() throws ServiceException, DaoException {
        List<Car> carList = List.of(expectedCar, mockCar);
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findAll()).thenReturn(carList);
        doNothing().when(transaction).endSingleQuery();

        List<Car> expectedCarList = carService.findCarList();

        verify(transaction).initSingleQuery(dao);
        verify(dao).findAll();
        verify(transaction).endSingleQuery();
        Assert.assertFalse(expectedCarList.isEmpty());
    }

    @Test
    public void findCarByIdTest() throws ServiceException, DaoException {
        long id = 101;
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findById(id)).thenReturn(Optional.of(expectedCar));
        doNothing().when(transaction).endSingleQuery();

        Optional<Car> car = carService.findCarById(id);

        verify(transaction).initSingleQuery(dao);
        verify(dao).findById(id);
        verify(transaction).endSingleQuery();
        Assert.assertTrue(car.isPresent());
    }

    @Test
    public void updateCarTest() throws DaoException, ServiceException {
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.update(expectedCar)).thenReturn(true);
        doNothing().when(transaction).endSingleQuery();

        carService.updateCar(expectedCar);

        verify(dao).update(expectedCar);
    }



}
