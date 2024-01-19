package uk.gov.dwp.health.atw.msclaimtopdf.models.enums;

public enum ClaimType {
  EQUIPMENT_OR_ADAPTATION("Specialist equipment"),
  TRAVEL_TO_WORK("Travel to or from work"),
  SUPPORT_WORKER("Support worker"),
  ADAPTATION_TO_VEHICLE("Adaptation to Vehicle");

  private final String label;

  ClaimType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}