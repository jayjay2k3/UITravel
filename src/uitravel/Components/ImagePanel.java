/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uitravel.Components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class ImagePanel extends javax.swing.JLayeredPane {

    private Icon backgroundImage;
    private float opcatity=1;
    private boolean isTransparent=true;

    public ImagePanel() {
        initComponents();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opcatity));
            int width = this.getWidth();
            int height = this.getHeight();
            Image suffix = ((ImageIcon) backgroundImage).getImage();
            g2d.drawImage(suffix, 0, 0, width, height, this);

            if(isTransparent){
                GradientPaint gradient = new GradientPaint(0, 0, new Color(1f, 1f, 1f, 1f), width, 0, new Color(1f, 1f, 1f, 0f));
                g2d.setPaint(gradient);
                g2d.fill(new Rectangle2D.Double(0, 0, width, height));
            }
            
            g2d.dispose();
        }
    }
    
    public Icon getbackgroundImage(){
        return backgroundImage;
    }
    public void setbackgroundImage(Icon backgroundImage){
        this.backgroundImage= backgroundImage;
    }
    public void setisTransparent(boolean isTransparent){
        this.isTransparent= isTransparent;
    }
    public boolean getisTransparent(){
        return isTransparent;
    }
    public void setOpcatity(float opcatity){
        this.opcatity= opcatity;
    }
    public float getOpcatity(){
        return opcatity;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
