package org.crud;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.crud.empleados.dao.PuestoDAO;
import org.crud.empleados.domain.Puesto;

public class PuestosDialog extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = -3929029475386893675L;

    public JButton agregarPuesto = new JButton("Agregar puesto");
    public JButton borrarPuesto = new JButton("Borrar puesto");
    private JTable puestosTable = new JTable();
    private DefaultTableModel puestosTableModel = new DefaultTableModel();
    private JScrollPane puestosTableScroll = new JScrollPane(puestosTable);

    public PuestosDialog(Window parent) {
        setTitle("Puestos");
        setSize(400, 300);
        this.setResizable(false);
        setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLayout(null);
        agregarPuesto.setBounds(20, 20, 130, 25);
        borrarPuesto.setBounds(160, 20, 130, 25);
        this.add(agregarPuesto);
        this.add(borrarPuesto);
        puestosTableScroll.setBounds(20, 60, 350, 200);
        puestosTable.setModel(puestosTableModel);
        this.add(puestosTableScroll);
        puestosTableModel.addColumn("id");
        puestosTableModel.addColumn("Nombre");
        agregarPuesto.addActionListener(event -> agregarPuestoClicked(event));
        borrarPuesto.addActionListener(event -> borrarPuestoClicked(event));
        refresh();
    }

    private void borrarPuestoClicked(ActionEvent event) {
        int row = puestosTable.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                    "¿Esta seguro de que desea borrar el puesto?", "Pregunta", JOptionPane.YES_NO_OPTION)) {
                Long id = (Long) puestosTableModel.getValueAt(row, 0);
                PuestoDAO puestoDAO = new PuestoDAO();
                try {
                    puestoDAO.deletePuesto(id);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                refresh();
            }
        }

    }

    private void agregarPuestoClicked(ActionEvent event) {
        String input = JOptionPane.showInputDialog("Agregar puesto");
        if (input != null && !input.isEmpty()) {
            if(puestoExiste(input)) {
                JOptionPane.showMessageDialog(this, "El puesto ya existe", "Error",JOptionPane.ERROR_MESSAGE);
            }else {
                try {
                    PuestoDAO puestoDAO = new PuestoDAO();
                    Puesto puesto = new Puesto();
                    puesto.setNombre(input);
                    puestoDAO.savePuesto(puesto);                
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }                
            }
        }
        this.refresh();
    }

    private boolean puestoExiste(String nombrePuesto)  {
        try {
            PuestoDAO puestoDAO = new PuestoDAO();
            return puestoDAO.puestoExiste(nombrePuesto);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    private void refresh() {
        PuestoDAO puestoDAO = new PuestoDAO();
        puestosTableModel.setRowCount(0);
        List<Puesto> puestos = puestoDAO.getPuestos();
        puestos.stream().forEach(puesto -> {
            Object[] row = new Object[] { puesto.getId(), puesto.getNombre() };
            puestosTableModel.addRow(row);
        });

    }

}
