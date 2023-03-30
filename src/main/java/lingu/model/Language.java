package lingu.model;

public enum Language {

  ENGLISH("EN"),
  GERMAN("DE"),
  SPANISH("ES"),
  FRENCH("FR");

  private String code;

  private Language(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

}
