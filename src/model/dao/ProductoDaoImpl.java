package model.dao;

import Datasource.DataSource;
import com.sun.jdi.connect.spi.Connection;
import model.entities.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao{
    private Connection conection;

    public ProductoDaoImpl (Connection conection){
        this.conection = conection;
    }

    @Override
    public List<Producto> findAll() {
       List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String sql = " select * from producto ";
            ps = conection.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                Producto prod = new Producto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setDescripcion(rs.getString("Descripcion"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));

                productos.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return productos;
    }

    @Override
    public Producto findById(int codigo) {
        Producto prod = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String sql = " select * from producto where codigo = ?";
            ps = conection.prepareStatement(sql);

            ps.setInt(1,codigo);
            rs = ps.executeQuery();

            if (rs.next()){
                prod = new Producto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setDescripcion(rs.getString("Descripcion"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return prod;
    }

    @Override
    public Producto save(Producto Producto) {

        PreparedStatement ps = null;

        try{
            String sql = "insert into producto (codigo,descripcion,precio,stock) values(?,?,?,?)";
            ps = conection.prepareStatement(sql);
            ps.setInt(1, Producto.getCodigo());
            ps.setString(2, Producto.getDescripcion());
            ps.setDouble(3, Producto.getPrecio());
            ps.setInt(4, Producto.getStock());

            int fila = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataSource.closeStatement(ps);
        }
        return Producto;
    }

    @Override
    public void update(Producto Producto) {
        PreparedStatement ps = null;

        try{
            String sql = "update productol set descripcion= ?, precio= ?, stock= ? where codigo = ?" ;
            ps = conection.prepareStatement(sql);
            ps.setString(1, Producto.getDescripcion());
            ps.setDouble(2, Producto.getPrecio());
            ps.setInt(3, Producto.getStock());
            ps.setInt(4, Producto.getCodigo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataSource.closeStatement(ps);
        }
    }

    @Override
    public void delete(Producto Producto) {
        PreparedStatement ps = null;

        try{
            String sql = "delete from producto where codigo = ?";
            ps = conection.prepareStatement(sql);
            ps.setInt(1, Producto.getCodigo());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            DataSource.closeStatement();
        }

    }
}
