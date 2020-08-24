package Exeption_Handler;

public class ForbiddenVacationDate extends Exception {
    public ForbiddenVacationDate() {
        super("درخواست مرخصی داده شده با درخواست های قبلی تداخل دارد");
    }
}
