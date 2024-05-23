package gr.scytalys.team3.Technikon.service;

public interface EnumValidator<T> {
    boolean typeOfRepairValidate(T value);
    boolean statusOfRepairValidate(T value);
}
