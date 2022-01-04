package ru.vsu.cs.avdeeva_p_a;

import java.util.List;

public class UniversityEntrant {

    private final String fio;
    private final int rusLanguageGrade;
    private final int mathGrade;
    private final int physicsGrade;
    private final String availabilityOfCertificate;
    private final double arithmeticMeanGrade;

    public UniversityEntrant(List<String> list) {
        this.fio = list.get(0);
        this.mathGrade = Integer.parseInt(list.get(1));
        this.physicsGrade = Integer.parseInt(list.get(2));
        this.rusLanguageGrade = Integer.parseInt(list.get(3));
        this.availabilityOfCertificate = list.get(4);
        arithmeticMeanGrade = (double) (mathGrade+physicsGrade+rusLanguageGrade)/3;
    }

    public String getFio() {
        return fio;
    }

    public int getRusLanguageGrade() {
        return rusLanguageGrade;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public int getPhysicsGrade() {
        return physicsGrade;
    }

    public String getAvailabilityOfCertificate() {
        return availabilityOfCertificate;
    }

    public double getArithmeticMeanGrade() {
        return arithmeticMeanGrade;
    }

    @Override
    public String toString() {
        return fio + " " + mathGrade + " " + physicsGrade
                + " " + rusLanguageGrade + " " + availabilityOfCertificate;
    }
}
