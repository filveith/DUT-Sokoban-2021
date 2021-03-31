package jeu;

public class Case {

    char nature;

    Case (char nature_) {
        this.nature = nature_;
    }

    public char getNature() {
        return this.nature;
    }

    public void setNature(char nat) {
        this.nature = nat;
    }
}