package model.Services;

import model.entities.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getList();
    Producto findById (int codigo);
    Producto save (Producto Producto);
    void update(Producto Producto);
    void delete(Producto Producto);
}
