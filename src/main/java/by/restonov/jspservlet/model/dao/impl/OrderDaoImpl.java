package by.restonov.jspservlet.model.dao.impl;

import by.restonov.jspservlet.exception.DaoException;
import by.restonov.jspservlet.model.dao.AbstractDao;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao {
    @Override
    public List findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Object> findById(Object id) throws DaoException {
        return null;
    }

    @Override
    public Optional<Object> findByName(String name) throws DaoException {
        return null;
    }

    @Override
    public boolean add(Object entity) throws DaoException {
        return false;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public boolean delete(Object id) throws DaoException {
        return false;
    }
}
