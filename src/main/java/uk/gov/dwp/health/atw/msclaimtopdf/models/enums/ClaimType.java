package uk.gov.dwp.health.atw.msclaimtopdf.models.enums;

public enum ClaimType {
  EQUIPMENT_OR_ADAPTATION("Specialist equipment"),
  TRAVEL_TO_WORK("Travel to or from work"),
  SUPPORT_WORKER("Support worker");

  private final String label;

  ClaimType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}