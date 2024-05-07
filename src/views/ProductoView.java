package views;



import model.Services.ProductoService;
import model.Services.ProductoServiceImpl;
import model.entities.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import java.util.Date;
import java.util.List;

public class ProductoView extends JPanel {

    ProductoService service;
    JTable listadoTable;
    private JPanel formPanel;
    JTextField _codigo;
    JTextField _descripcion;
    JTextField _precio;
    JTextField _stock;
    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton clearButton;

    public ProductoView() {

        service = new ProductoServiceImpl();

        this.setLayout(new GridLayout(2, 1));

        listadoTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(listadoTable);
        this.add(scrollPane, BorderLayout.CENTER);

        crearFormulario();

        this.add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(true);

        listadoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = listadoTable.getSelectedRow();
            }
        });

        showCars();
        this.setVisible(true);
    }

    private void crearFormulario() {
        // Crear formulario
        formPanel = new JPanel(new GridLayout(5, 2));

        formPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Producto"));
        formPanel.add(new JLabel("codigo:"));
        _codigo = new JTextField();
        formPanel.add(_codigo);

        formPanel.add(new JLabel("Decripcion:"));
        _descripcion = new JTextField();
        formPanel.add(_descripcion);

        formPanel.add(new JLabel("Precio:"));
        _precio = new JTextField();
        formPanel.add(_precio);

        formPanel.add(new JLabel("Stock:"));
        _stock = new JTextField();
        formPanel.add(_stock);




        addButton = new JButton("Nuevo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });
        formPanel.add(addButton);

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCar();
            }
        });
        formPanel.add(updateButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCar();
            }
        });
        formPanel.add(deleteButton);

        clearButton = new JButton("Limpiar");

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        formPanel.add(clearButton);
    }

    private void showCars() {

        // Configurar modelo de datos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Descripcion");
        model.addColumn("precio");
        model.addColumn("stock");

        List<Producto> productos = service.getList();

        for (Producto producto : productos) {
            model.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock()

            });
        }

        listadoTable.setModel(model);
        formPanel.setVisible(true);
    }

    private void addCar() {
        int codigo = Integer.parseInt(_codigo.getText());
        String descripcion = _descripcion.getText();
        Double precio = Double.valueOf(_precio.getText());
        int stock = Integer.parseInt(_stock.getText());


        Producto producto = new Producto();

        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);


        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();

        service.save(producto);

        model.addRow(new Object[]{
                producto.getCodigo(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),

        });

        clearForm();
    }

    private void updateCar() {

        int codigo = Integer.parseInt(_codigo.getText());
        String descripcion = _descripcion.getText();
        double precio = Double.parseDouble(_precio.getText());
        int stock = Integer.parseInt(_stock.getText());


        Producto producto = new Producto();

        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(Double.valueOf(precio));
        producto.setStock(stock);


        service.update(producto);
        showCars();
    }

    private void deleteCar() {
        int codigo = Integer.parseInt(_codigo.getText());
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        service.delete(producto);
        showCars();
    }

    private void clearForm() {
        _codigo.setText("");
        _descripcion.setText("");
        _precio.setText("");
        _stock.setText("");
    }
}