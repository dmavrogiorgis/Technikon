package gr.scytalys.team3.Technikon.service;

/**
 * Used to validate TypeOfRepair and StatusOfRepair.
 * @param <T> a generic type to be able to use TypeOfRepair and StatusOfRepair.
 */
public interface EnumValidator<T> {
    boolean typeOfRepairValidate(T value);
    boolean statusOfRepairValidate(T value);
}
