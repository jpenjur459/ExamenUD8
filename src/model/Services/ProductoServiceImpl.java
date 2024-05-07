package model.Services;

import model.dao.DaoFactory;
import model.dao.ProductoDao;
import model.entities.Producto;

import java.util.List;

public class ProductoServiceImpl implements ProductoService{

    private ProductoDao Dao;
    public ProductoServiceImpl(){
        Dao = DaoFactory.createProductoDao();
    }

    @Override
    public List<Producto> findAll() {
        return Dao.findAll();
    }

    @Override
    public Producto findById(int codigo) {
        return Dao.findById(codigo);
    }

    @Override
    public Producto save(Producto Producto) {
        return Dao.save(Producto);
    }

    @Override
    public void update(Producto Producto) {
        Dao.update(Producto);
    }

    @Override
    public void delete(Producto Producto) {
        Dao.delete(Producto);
    }
}
