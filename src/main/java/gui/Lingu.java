package gui;

import gui.features.*;
import gui.lib.Environment;

import javax.swing.*;

public class Lingu {

  public static void main(String[] args) {
    setTheme();
    Lingu lingu = new Lingu();
    lingu.run();
  }

  private final Environment env;
  private final JFrame register;
  private final JFrame menu;
  private final JFrame learn;
  private final JFrame settings;

  public Lingu() {
    this(new Environment());
  }

  public Lingu(Environment env) {
    this.env = env;

    register = new Register(env);
    menu = new Menu(env);
    learn = new Learn(env);
    settings = new Settings(env);
  }

  public void run() {
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

}
