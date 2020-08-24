package Exeption_Handler;

public class ForbiddenDaysOff extends Exception {
    public ForbiddenDaysOff() {
        super("تعداد درخواست های مرخصی شما که در حالت بررسی است محدود است و الان امکان ثبت مرخصی جدید نیست");
    }
}
