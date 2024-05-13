package gr.scytalys.team3.Technikon.service;

public interface PropertyOwnerValidator {
    void validatePropertyOwner(String tin, String email, String phoneNumber);
    boolean isTINValid(String tin);
    boolean isEmailValid(String email);
}
