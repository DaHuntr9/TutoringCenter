package src;

public interface Users {
  //simple variable getters
  String getUserEmail(int UserNumber);
  String getUsername(int UserNumber);
  String getPassword(int UserNumber);
  String getFirstName(int UserNumber);
  String getLastName(int UserNumber);
  String getRole(int UserNumber);
  //simple variable setters
  void setUserEmail();
  void setUsername();
  void setPassword();
  void setFirstName(int UserNumber, String FirstName);
  void setLastName();
  //more complex object manipulation methods
  void createAppointment();
  void instantiateAssociations();
  void goToProfile();
  void goToTutorReviews();
  void goToGradesAttendence();
}
