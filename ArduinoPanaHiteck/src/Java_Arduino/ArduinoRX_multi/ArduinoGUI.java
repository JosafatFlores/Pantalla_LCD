package Java_Arduino.ArduinoRX_multi;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class ArduinoGUI extends javax.swing.JFrame implements ActionListener {

    PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    String msg;

    //Conexion de Java a Arduino
    public ArduinoGUI() {
        //manda indicador a la pantalla LCD
        initComponents();
        setLocationRelativeTo(null);

        //variables de calendar para la hora de sistema
        
        //conexion de Java a Arduino
        try {
            //Se inicia la comunicación con el Puerto Serie
            ino.arduinoTX("COM3", 9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(ArduinoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonHora = new javax.swing.JButton();
        BotonCaptura = new javax.swing.JButton();
        CapturaTexto = new javax.swing.JTextField();
        BotonTemp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BotonHora.setBackground(new java.awt.Color(255, 255, 255));
        BotonHora.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        BotonHora.setText("Hora");
        BotonHora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BotonHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonHoraActionPerformed(evt);
            }
        });

        BotonCaptura.setBackground(new java.awt.Color(255, 255, 255));
        BotonCaptura.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        BotonCaptura.setText("Mensaje");
        BotonCaptura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BotonCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCapturaActionPerformed(evt);
            }
        });

        CapturaTexto.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N

        BotonTemp.setBackground(new java.awt.Color(255, 255, 255));
        BotonTemp.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        BotonTemp.setText("Temperatura");
        BotonTemp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BotonTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTempActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotonHora, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotonTemp))
                    .addComponent(BotonCaptura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CapturaTexto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonHora, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CapturaTexto)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //al clickear en boton hora
    private void BotonHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonHoraActionPerformed
        Calendar c = new GregorianCalendar();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int segu = c.get(Calendar.SECOND);
        int mint = c.get(Calendar.MINUTE);
        if(hora > 12){
            hora= hora- 12;
        }
        msg = "";
        msg = Integer.toString(hora) + ":" + Integer.toString(mint) + ":" + segu + " ";
        try {
            
            //manda el mensaje de la hora del sistema btenida anteriormente
            ino.sendData(msg);
            msg = "";
            //System.out.println(msg);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(ArduinoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonHoraActionPerformed

    //al clickear en el boton enviar texto
    private void BotonCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCapturaActionPerformed
        try {
            //envia al Arduino el texto que se escribio en el text anteriormente
            
            msg = CapturaTexto.getText();
            ino.sendData(msg);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(ArduinoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonCapturaActionPerformed

    //al clickear en el boton de temperatura
    private void BotonTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTempActionPerformed
        try {
            //manda al IDE de Arduino el dato 2 para que realice la actividad que se encuentra en el IDE
            ino.sendData("2");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(ArduinoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonTempActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArduinoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCaptura;
    private javax.swing.JButton BotonHora;
    private javax.swing.JButton BotonTemp;
    private javax.swing.JTextField CapturaTexto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
