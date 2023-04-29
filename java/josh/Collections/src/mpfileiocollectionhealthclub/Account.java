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

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public void setPayTerm(char payTerm) {
        this.payTerm = payTerm;
    }

    public void setSupMember(int supMember) {
        this.supMember = supMember;
    }

    @Override
    public String toString() {
        return String.format("%s %c %c %d", ID, memberType, payTerm, supMember);
    }
}
