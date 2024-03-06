public enum Behavior {
    IDLE(0),CHARGING(1),SUSPISCIOUS(2);
    int i;
    Behavior(int i) {
        this.i = i;
    }
    public int getI() {
        return i;
    }
}
