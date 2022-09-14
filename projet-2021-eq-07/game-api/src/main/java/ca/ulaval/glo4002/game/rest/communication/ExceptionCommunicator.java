package ca.ulaval.glo4002.game.rest.communication;

import io.vavr.collection.HashMap;

public abstract class ExceptionCommunicator {

  private static final String unknownExceptionTitle = "UNKNOWN_EXCEPTION";
  private static final String unknownExceptionDescription = "Unknown Exception";
  protected HashMap<String, String> titles = HashMap.empty();
  protected HashMap<String, String> descriptions = HashMap.empty();

  public String getErrorTitleFromExceptionName(String exceptionName) {
    return this.titles.getOrElse(exceptionName, unknownExceptionTitle);
  }

  public String getErrorDescriptionFromExceptionName(String exceptionName) {
    return this.descriptions.getOrElse(exceptionName, unknownExceptionDescription);
  }
}
