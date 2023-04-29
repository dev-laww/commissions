package mpfileiocollectionhealthclub;

import java.io.Serial;
import java.io.Serializable;

public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 6174983902710942791L;
    private final String ID;
    private char memberType;
    private char payTerm;
    private int supMember;

    public Account(String id, char memberType, char payTerm, int supMember) {
        this.ID = id;
        this.memberType = memberType;
        this.payTerm = payTerm;
        this.supMember = supMember;
    }

    public String id() {
        return ID;
    }

    public char memberType() {
        return memberType;
    }

    public char payTerm() {
        return payTerm;
    }

    public int supMember() {
        return supMember;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public void setPayTerm(char payTerm) {
        this.payTerm = payTerm;
    }

    public void setSupMember(int supMember) {
        this.supMember = supMember;
    }

    public double calculateMembershipFee() {
        double miscFee = 0;
        double basicFee = 0;
        double supMemberFee = 0;
        double ratePerSupMember = memberType == 'R' ? 900 : 1000;

        switch (payTerm) {
            case 'C':
                basicFee = (0.85 * 12 * getMonthlyBasicFee());
                break;
            case 'M':
                basicFee = getMonthlyBasicFee();
                miscFee = 1000;
                break;
            case 'Q':
                basicFee = 1200;
                miscFee = 3890;
                break;
            case 'S':
                basicFee = 1500;
                miscFee = 4865;
                break;
        }

        if (supMember > 0) {
            supMemberFee = supMember * ratePerSupMember * getAdjustedMiscFee();
        }

        return miscFee + basicFee + supMemberFee;
    }

    private double getAdjustedMiscFee() {
        double adjustedMiscFee = 1;

        switch (payTerm) {
            case 'C':
                adjustedMiscFee = 0;
                break;
            case 'M':
                break;
            case 'Q':
                adjustedMiscFee = 3.89;
                break;
            case 'S':
                adjustedMiscFee = 4.865;
                break;
        }

        return adjustedMiscFee;
    }

    private double getMonthlyBasicFee() {
        double monthlyBasicFee = 0;

        switch (memberType) {
            case 'R':
                monthlyBasicFee = 1200;
                break;
            case 'V':
                monthlyBasicFee = 1500;
                break;
        }

        return monthlyBasicFee;
    }

    @Override
    public String toString() {
        return String.format("%s %c %c %d", ID, memberType, payTerm, supMember);
    }
}
