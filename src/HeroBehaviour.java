public enum HeroBehaviour {
    SLEEPING_SOUTH(0,3),SLEEPY_SOUTH(0,3),WALKING_SOUTH(4,10),
    SLEEPING_WEST(1,3),SLEEPY_WEST(1,3),WALKING_WEST(5,10),
    SLEEPING_NORTH(2,1),SLEEPY_NORTH(2,1),WALKING_NORTH(6,10),
    SLEEPING_EAST(3,3),SLEEPY_EAST(3,3),WALKING_EAST(7,10);
    private int attitude;
    private int numberOfFrameInCurrentAttitude;
    HeroBehaviour(int i, int j) {
        this.attitude = i;
        this.numberOfFrameInCurrentAttitude=j;
    }
    public int getAttitude() {
        return attitude;
    }

    public int getNumberOfFrameInCurrentAttitude() {
        return numberOfFrameInCurrentAttitude;
    }
}
