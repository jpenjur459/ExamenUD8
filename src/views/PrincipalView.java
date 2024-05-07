package views;

import model.entities.Producto;
import views.ProductoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalView extends JFrame {
    public PrincipalView() throws HeadlessException {
        setTitle("Tienda");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itemProducto = new JMenuItem("producto");

        itemProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoView productoview = new ProductoView();
                add(productoview);
                productoview.setVisible(true);
                repaint();
            }
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(itemProducto);
        menu.addSeparator();
        menu.add(itemSalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setVisible(true);
    }
}
