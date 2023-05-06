package gui;

import gui.features.*;
import javax.swing.*;

public class Lingu {

  public static void main(String[] args) {
    setTheme();

    register.setVisible(true);
  }

  private static void setTheme() {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Learn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Learn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Learn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Learn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  private static final JFrame register = new Register();
  // private static final JFrame menu = new Menu();
  // private static final JFrame learn = new Learn();
  // private static final JFrame settings = new Settings();

}
