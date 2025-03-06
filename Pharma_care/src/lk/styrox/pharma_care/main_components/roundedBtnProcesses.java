package lk.styrox.pharma_care.main_components;

import lk.styrox.pharma_care.sub_components.*;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author thara
 */
public class roundedBtnProcesses extends JButton {

    private final Color color1 = Color.WHITE;
    private final Color color2 = Color.WHITE;
    
    private Color borderColor;
    private int thickness;
    private int radius = 10;

//    private Color color1 = new Color(96, 131, 255, 255);
//    private Color color2 = new Color(167, 95, 255, 255);
//    private int round = 10;

    public roundedBtnProcesses() {
        setContentAreaFilled(true);
//        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(10, 20));
//        setBorder(new EmptyBorder(10, 20, 10, 20));
        setForeground(Color.black);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    public void repaintBtn(int round) {
        if (round > 0 && round <= 30) {
            this.radius = round;
            repaint();
        }
    }

    public void repaintBtn(Color color, int thickness) {
        if (thickness > 0 && thickness <= 10 && color != null) {
            this.thickness = thickness;
            this.borderColor = color;
            setForeground(borderColor);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, width, height, radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(thickness, thickness, width-(thickness*2), height-(thickness*2), radius, radius);
        g2.dispose();

        super.paintComponent(g);
    }
}
