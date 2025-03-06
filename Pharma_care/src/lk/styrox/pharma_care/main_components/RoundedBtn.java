package lk.styrox.pharma_care.main_components;

import lk.styrox.pharma_care.sub_components.*;
import com.formdev.flatlaf.FlatClientProperties;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author thara
 */
public class RoundedBtn extends JButton {

    private Color color1 = Color.WHITE;
    private Color color2 = Color.WHITE;

    private int round = 10;

    public RoundedBtn() {
        setContentAreaFilled(false);
        this.setFont(new Font("SansSerif", Font.BOLD, 14));
    }

    public void repaintBtn(Color color1, Color color2) {
        if (color1 != null && color2 != null) {
            this.color1 = color1;
            this.color2 = color2;
            repaint();
            
//            this.updateUI();
//            setForeground(Color.WHITE);

//            if (color1 == Color.WHITE && color2 == Color.WHITE) {
//                setForeground(new Color(88, 88, 88, 255));
//            }

        }
    }

    public void repaintBtn(int round) {
        if (round > 0 && round <= 30) {
            this.round = round;
            repaint();
        }
    }

    public void repaintBtn(ImageIcon icon) {
        if (icon != null) {
            try {
                this.setIcon(new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
//        super.paintComponent(grphcs);
//         super = (RoundedBtn)this;
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();
        GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, width, height, round, round);
        g2.dispose();

        super.paintComponent(grphcs);
    }
}
