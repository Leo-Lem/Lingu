package cli.lib;

import backend.services.interfaces.Localizer;

public class Message {
  private final Localizer localizer;

  private String content = "";

  public Message(Localizer localizer) {
    this.localizer = localizer;
  }

  public Message set(Object localizationKey, Object... arguments) {
    this.content = localizer.localize(localizationKey, arguments);
    return this;
  }

  public Message asLingu() {
    return asPerson("Lingu");
  }

  public Message asLearner(String name) {
    return asPerson(name);
  }

  public Message asInvalidInputError() {
    return asError("INVALID_INPUT");
  }

  public Message asOption(Object identifier) {
    content = "  (" + identifier.toString() + ") " + content;
    return this;
  }

  public Message asError(Object titleLocalizationkey) {
    content = "\033[1A\033[K[" + localizer.localize(titleLocalizationkey) + ": " + content + "] ";
    return this;
  }

  public Message asPerson(String name) {
    content = "«" + name + "» " + content;
    return this;
  }

  public String build() {
    return content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    else if (o == null || getClass() != o.getClass())
      return false;
    else {
      Message message = (Message) o;
      return message.content.equals(content) && message.localizer.equals(localizer);
    }
  }

}
