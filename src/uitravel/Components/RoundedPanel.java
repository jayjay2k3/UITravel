/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author ACER
 */
public class RoundedPanel extends javax.swing.JPanel {
    private int radius;
    private boolean withBorder;
    private Color borderColor;

    /**
     * Creates new form RoundedPanel
     */
    public RoundedPanel() {
        initComponents();
    }
    
    public RoundedPanel(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
        }
    public void setRadius(int radius) {
        this.radius = radius;
    }
     public int getRadius() {
        return radius;
    }
    public boolean getWithBorder(){
        return withBorder;
    }
    
    public void setWithBorder(boolean withBorder){
         this.withBorder = withBorder;
    }
    public void setBorderColor(Color borderColor){
        this.borderColor = borderColor;
    }
    public Color getBorderColor(){
        return borderColor;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Background color
        g2.setColor(getBackground());
        
        // Create rounded rectangle shape
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
        
        // Fill the rounded rectangle
        g2.fill(roundedRectangle);
        
        if(withBorder){
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0,getWidth()-1, getHeight()-1, radius, radius);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(102, 0, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}