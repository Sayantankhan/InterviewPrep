
interface Relation {
    boolean knows(int i, int j);
}

public class FindCelebrity implements Relation{
    //Find the Celebrity
    public int findCelebrity(int n) {
        // rather than checking each one of them who knows what , lets find out who could be possible celebrity
        // verify possible celeb is our celeb or not
        // if 0 knows 1 means 0 is not a celeb || but 1 can
        // if 2 knows 1 means 2 is not a celeb || but 1 can
        // if 1 knows 4 means 1 is not a celeb || but 4 can

        int possible_celeb = 0;
        for(int i=0; i < n; i++) {
            if(knows(possible_celeb, i)) possible_celeb = i;
        }
        // verify possible celeb is our celeb or not
        return (check_celeb(possible_celeb, n)) ? possible_celeb : -1;
    }

    boolean check_celeb(int possible_celeb, int n) {
        for(int i = 0; i < n; i++) {
            if(possible_celeb == i) continue;
            if(knows(possible_celeb, i) || !knows(i, possible_celeb)) return false;

        }
        return true;
    }

    @Override
    public boolean knows(int i, int j) {
        return true;
    }
}
