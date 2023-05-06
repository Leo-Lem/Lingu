package gui;

import gui.features.*;
import gui.lib.Environment;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.*;

public class Lingu extends JFrame {

  public static void main(String[] args) {
    setTheme();
    Lingu lingu = new Lingu();
    lingu.run();
  }

  private final CardLayout cards = new CardLayout();;
  private final Environment env;

  public Lingu() {
    this(new Environment());
  }

  public Lingu(Environment env) {
    this.env = env;

    setTitle("Lingu");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    Container pane = getContentPane();

    pane.setLayout(cards);
    // pane.add("register", new Register(env));
    pane.add("menu", new Menu(env, i -> navigateTo(i)));
    // pane.add("learn", new Learn(env));
    // pane.add("settings", new Settings(env));
  }

  public void run() {
    setSize(500, 300);
    setVisible(true);
  }

  private static void setTheme() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void navigateTo(String identifier) {
    cards.show(getContentPane(), identifier);
  }

}
