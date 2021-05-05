public class Case {

    private char nature;

    /**
     * Constructeur de Case
     *
     * @param nature
     */
    Case(char nature){
        this.nature = nature;
    }

    /**
     * get la nature d'une case
     *
     * @return nature de la case
     */
    public char getNature() {
        return this.nature;
    }

    /**
     * Permet de donner/changer la nature d'une case
     *
     * @param nature la nature à donner
     */
    public void setNature(char nature) {
        this.nature = nature;
    }
}
