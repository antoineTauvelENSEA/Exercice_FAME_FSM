public enum EnemyBehaviour {
    IDLE(0),CHARGING(1),SUSPISCIOUS(2);
    int i;
    EnemyBehaviour(int i) {
        this.i = i;
    }
    public int getI() {
        return i;
    }
}
