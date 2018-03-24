package sda.code.intermediate.part3.answers.smalldata.hotele;

import lombok.Data;

@Data
public class KodPocztowy implements Comparable<KodPocztowy> {
    private final String kod;

    @Override
    public int compareTo(KodPocztowy o) {
        return kod.compareTo(o.kod);
    }
}
