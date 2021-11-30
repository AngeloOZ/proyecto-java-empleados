package org.crud;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import org.crud.empleados.dao.EmpleadoDAO;
import org.crud.empleados.dao.EmpleadoImagenDAO;
import org.crud.empleados.dao.PuestoDAO;
import org.crud.empleados.domain.Empleado;
import org.crud.empleados.domain.EmpleadoImagen;
import org.crud.empleados.domain.Puesto;
import org.crud.helpers.Constants;
import org.crud.helpers.ImageScaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;

public class MainForm extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 4720079838247703732L;

    private static final Logger logger = LoggerFactory.getLogger(MainForm.class);

    private JButton addEmpleadoBtn = new JButton("Agregar empleado");
    private JButton deleteEmpleadoBtn = new JButton("Borrar empleado");
    private JButton limpiarCamposBtn = new JButton("Limpiar Campos");
    private JButton agregarFotoBtn = new JButton("Agregar Foto");
    private JButton asignarPuestoBtn = new JButton("Asignar Puesto");
    private JLabel idLabel = new JLabel("Id");
    private JTextField idTextField = new JTextField(20);
    private JLabel nombreLabel = new JLabel("Nombre");

    private JLabel puestoLabel = new JLabel("Puesto");
    private JTextField puestoTextField = new JTextField();

    private JLabel fotoLabel = new JLabel();

    private JTextField nombreTextField = new JTextField(20);
    private JLabel correoLabel = new JLabel("Correo");
    private JTextField correoTextField = new JTextField(20);
    private JTable empleadosTable = new JTable();
    private DefaultTableModel empleadosTableModel = new DefaultTableModel();
    private JScrollPane empleadosTableScroll = new JScrollPane(empleadosTable);

    public MainForm() {
        setTitle("Java Crud");
        setSize(500, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        Container c = this.getContentPane();
        c.setLayout(null);
        idLabel.setBounds(20, 20, 60, 25);
        puestoLabel.setBounds(360, 20, 60, 25);
        nombreLabel.setBounds(20, 110, 60, 25);
        correoLabel.setBounds(20, 140, 60, 25);
        fotoLabel.setBounds(220, 20, 80, 80);
        c.add(idLabel);
        c.add(nombreLabel);
        c.add(correoLabel);
        c.add(fotoLabel);
        c.add(puestoLabel);
        idTextField.setBounds(80, 20, 60, 25);
        idTextField.setEditable(false);
        nombreTextField.setBounds(80, 110, 380, 25);
        correoTextField.setBounds(80, 140, 380, 25);
        puestoTextField.setBounds(360, 50, 80, 25);
        puestoTextField.setEditable(false);
        c.add(idTextField);
        c.add(nombreTextField);
        c.add(correoTextField);
        c.add(puestoTextField);
        addEmpleadoBtn.setBounds(20, 180, 140, 25);
        deleteEmpleadoBtn.setBounds(170, 180, 140, 25);
        limpiarCamposBtn.setBounds(320, 180, 140, 25);
        agregarFotoBtn.setBounds(20, 220, 140, 25);
        asignarPuestoBtn.setBounds(170, 220, 140, 25);
        c.add(addEmpleadoBtn);
        c.add(deleteEmpleadoBtn);
        c.add(limpiarCamposBtn);
        c.add(agregarFotoBtn);
        c.add(asignarPuestoBtn);
        empleadosTable.setModel(empleadosTableModel);
        empleadosTableModel.addColumn("id");
        empleadosTableModel.addColumn("nombre");
        empleadosTableModel.addColumn("correo");
        empleadosTableModel.addColumn("puesto");
        empleadosTableScroll.setBounds(20, 260, 440, 340);
        c.add(empleadosTableScroll);

        addEmpleadoBtn.addActionListener(this::addEmpleadoClick);
        empleadosTable.getSelectionModel().addListSelectionListener(this::empleadosTableChange);
        deleteEmpleadoBtn.addActionListener(this::deleteEmpleadoClick);
        limpiarCamposBtn.addActionListener(event -> limpiarCampos());
        agregarFotoBtn.addActionListener(this::agregarFotoClicked);
        asignarPuestoBtn.addActionListener( event -> asignarPuestoClicked(event) );
        this.setJMenuBar( makeMenuBar() );
        refresh();

    }
    
    private void asignarPuestoClicked(ActionEvent event) {
        PuestosDialog puestosDialog = new PuestosDialog(this);
        puestosDialog.setVisible(true);
        PuestoDAO puestoDAO = new PuestoDAO();
        List<Puesto> puestos = puestoDAO.getPuestos();
        String[] puestosChoice =  puestos.stream().map(puesto->puesto.getNombre()).collect( Collectors.toList() ).toArray(new String[] {});
        
        String input = (String) JOptionPane.showInputDialog(null, "Seleccione un puesto",
            "Puestos", JOptionPane.QUESTION_MESSAGE, null, 
            puestosChoice, 
            puestosChoice[0]);
        if(input != null) {
             List<Puesto> puestosFilter = puestos.stream().filter(puesto-> puesto.getNombre().equals(input) ).collect(Collectors.toList());
             if( !CollectionUtils.isEmpty(puestosFilter)) {
                 Puesto puesto = puestosFilter.get(0);
                 Long puestoId = puesto.getId();
                 if( !this.idTextField.getText().isEmpty() ) {
                     Long empleadoId = Long.parseLong(this.idTextField.getText());
                     EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                     try {
                        empleadoDAO.setPuestoToEmpleado(empleadoId, puestoId);
                        refresh();
                        limpiarCampos();
                    } catch (ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
                    }    
                 } 
             }
        }
        
    }

    private JMenuBar makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCatalogo = new JMenu("Catalogo");
        menuBar.add(menuCatalogo);
        JMenuItem puestosMenu = new JMenuItem("Puestos");
        menuCatalogo.add(puestosMenu);
        puestosMenu.addActionListener(this::showPuestos);
        return menuBar;
    }

    private void showPuestos(ActionEvent event) {
        PuestosDialog puestosDialog = new PuestosDialog(this);
        puestosDialog.setVisible(true);
    }

    private void refresh() {
        try {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            List<Empleado> empleados = empleadoDAO.getEmpleados();
            empleadosTableModel.setRowCount(0);
            empleados.stream().forEach(empleado -> {
                empleadosTableModel
                        .addRow(new Object[] { empleado.getId(), empleado.getNombre(), empleado.getCorreo() , empleado.getPuestoNombre() });
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void addEmpleadoClick(ActionEvent event) {
        Empleado empleado = new Empleado();
        String idText = idTextField.getText();
        if (!idText.isEmpty()) {
            empleado.setId(Long.parseLong(idText));
        }
        empleado.setNombre(nombreTextField.getText());
        empleado.setCorreo(correoTextField.getText());
        if (empleadoIsValido(empleado)) {
            if (!isCorreoValido(empleado.getCorreo())) {
                JOptionPane.showMessageDialog(this, "El correo no es valido", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                try {
                    empleadoDAO.saveEmpleado(empleado);
                    JOptionPane.showMessageDialog(this, "Exito al agregar empleado", "Informacion",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.limpiarCampos();
                    this.refresh();
                } catch (ClassNotFoundException | SQLException e) {
                    logger.error(e.getMessage(), e);
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void empleadosTableChange(ListSelectionEvent event) {
        int row = empleadosTable.getSelectedRow();
        if (row != -1) {
            Long id = (Long) empleadosTableModel.getValueAt(row, 0);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            try {
                Empleado empleado = empleadoDAO.getEmpleado(id);
                idTextField.setText(String.valueOf(empleado.getId()));
                nombreTextField.setText(empleado.getNombre());
                correoTextField.setText(empleado.getCorreo());
                puestoTextField.setText(empleado.getPuestoNombre());
                this.refreshImage();
            } catch (ClassNotFoundException | SQLException e) {
                logger.error(e.getMessage(), e);
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        nombreTextField.setText("");
        correoTextField.setText("");
        puestoTextField.setText("");
        fotoLabel.setIcon(null);
    }

    private void deleteEmpleadoClick(ActionEvent event) {
        String idField = idTextField.getText();
        if (!idField.isEmpty()) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                    "�Esta seguro de que desea borrar el empleado?", "Pregunta", JOptionPane.YES_NO_OPTION)) {
                long id = Long.parseLong(idField);
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                try {
                    empleadoDAO.deleteEmpleado(id);
                } catch (ClassNotFoundException | SQLException e) {
                    logger.error(e.getMessage(), e);
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                this.limpiarCampos();
                this.refresh();
                JOptionPane.showMessageDialog(this, "Exito al borrar empleado", "Informacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un empleado para borrar", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean empleadoIsValido(Empleado empleado) {
        return !empleado.getCorreo().isEmpty() && !empleado.getNombre().isEmpty();
    }

    public boolean isCorreoValido(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void agregarFotoClicked(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter imageFile = new FileFilter() {
            @Override
            public String getDescription() {
                return "*.jpg|*.png";
            }

            @Override
            public boolean accept(File file) {
                String name = file.getName().toLowerCase();
                if (file.isDirectory()) {
                    return true;
                }
                if (name.endsWith(".png")) {
                    return true;
                } else if (name.endsWith(".jpg")) {
                    return true;
                } else if (name.endsWith(".jpeg")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        fileChooser.setFileFilter(imageFile);
        fileChooser.setMultiSelectionEnabled(false);
        if (JFileChooser.CANCEL_OPTION != fileChooser.showDialog(this, "Selecciona una imagen")) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageScaling imageScaling = new ImageScaling(Constants.SCALE_FACTOR);
                byte[] data = Files.readAllBytes(Paths.get(selectedFile.toURI()));
                byte[] newData = imageScaling.scaleImage(data);
                String byteData = new String(Base64.encodeBase64(newData), "utf-8");
                if (!this.idTextField.getText().isEmpty()) {
                    EmpleadoImagenDAO empleadoImagenDAO = new EmpleadoImagenDAO();
                    Long id = Long.parseLong(this.idTextField.getText());
                    EmpleadoImagen empleadoImagen = new EmpleadoImagen();
                    empleadoImagen.setId(id);
                    empleadoImagen.setMiniature(byteData);
                    empleadoImagenDAO.saveEmpleadoImagen(empleadoImagen);
                    this.refreshImage();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshImage() {
        if (!this.idTextField.getText().isEmpty()) {
            Long id = Long.parseLong(this.idTextField.getText());
            EmpleadoImagenDAO empleadoImagenDAO = new EmpleadoImagenDAO();
            String imagenData = empleadoImagenDAO.getImagen(id);
            if (imagenData != null) {
                byte[] data = Base64.decodeBase64(imagenData.getBytes());
                ImageIcon image = new ImageIcon(data);
                this.fotoLabel.setIcon(image);
            }else {
                this.fotoLabel.setIcon(null);
            }
        }else {
            this.fotoLabel.setIcon(null);
        }

    }

}
