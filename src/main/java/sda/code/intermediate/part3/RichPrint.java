package sda.code.intermediate.part3;

public class RichPrint {

    public static void println(String msg) {
        System.err.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void println(Object obj) {
        if (obj == null) {
            println("null");
        } else {
            println(obj.toString());
        }
    }
}
