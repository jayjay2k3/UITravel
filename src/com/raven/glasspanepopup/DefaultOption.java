package com.raven.glasspanepopup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;

/**
 *
 * @author Raven
 */
public class DefaultOption implements Option {

    private float animate;
    private Component referenceComponent; // Add a reference component

    @Override
    public String getLayout(Component parent, float animate) {
        float an = 20f / parent.getHeight();
        float space = 0.5f + an - (animate * an);
        return "pos 0.5al " + space + "al";
    }
     public void setReferenceComponent(Component referenceComponent) {
        this.referenceComponent = referenceComponent;
    }
    @Override     
    public LayoutCallback getLayoutCallBack(final Component parent) {
       return new LayoutCallback() {
            @Override
            public void correctBounds(ComponentWrapper cw) {
                 if (parent.isVisible() && referenceComponent != null) {
                    Component comp = (Component) cw.getComponent(); // Cast to Component
                    Point parentLocation = parent.getLocationOnScreen();
                    Point referenceLocation = referenceComponent.getLocationOnScreen();
                    
                    int x = referenceLocation.x - parentLocation.x;
                    int y = referenceLocation.y - parentLocation.y + referenceComponent.getHeight();
                    
                    cw.setBounds(x, y, cw.getWidth(), cw.getHeight());
                } else {
                    super.correctBounds(cw);
                }
            }
        };
    }


    @Override
    public boolean useSnapshot() {
        return true;
    }
    
    @Override
    public boolean closeWhenPressedEsc() {
       return true;
    }

    @Override
    public boolean closeWhenClickOutside() {
        return true;
    }

    @Override
    public boolean blockBackground() {
        return true;
    }

    @Override
    public Color background() {
        return new Color(100, 100, 100);
    }

    @Override
    public float opacity() {
        return 0.5f;
    }

    @Override
    public int duration() {
        return 300;
    }

    @Override
    public float getAnimate() {
        return animate;
    }

    @Override
    public void setAnimate(float animate) {
        this.animate = animate;
    }
}
