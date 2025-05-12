import java.util.*;

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isValidPosition(int n) {
        return (this.x < n && this.y < n);
    }

    boolean isValidPosition(Position pos1, Position pos2, int size) {
        return ((this.x-size >= pos1.x && this.x+size <= pos2.x) && (this.y-size >= pos2.y && this.y+size <= pos1.y));
    }

    @Override
    public boolean equals(Object obj) {
        return x == ((Position)obj).x && y == ((Position)obj).y;
    }


    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}

class Ship {

    int shipId;
    Position pos;
    int size;

    Ship(int shipId, int size, Position pos){
        this.shipId = shipId;
        this.pos = pos;
        this.size = size;
    }
}

class Player {
    private final int world_size;
    int id;
    String name;
    final HashMap<Position, Integer> ships;
    boolean[][] enemey_map;
    Position enemey_teritory;

    Player(int id, int size, String name) {
        this.id = id;
        this.name = name;
        this.world_size = size;
        ships = new HashMap<>();
        enemey_map = new boolean[world_size][world_size];
    }

    void addShip(Position p, int id) {
        ships.put(p, id);
    }

    Map<Position, Integer> getShips() {
        return this.ships;
    }

    public boolean[][] getEnPos() {
        return enemey_map;
    }

    public void setEnPos(int[][] map) {
        int n = map.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(Math.abs(map[i][j]) == id) {
                    enemey_map[i][j] = false;
                } else {
                    enemey_map[i][j] = true;
                }
            }
        }
    }

    public List<Position> getEnTeri() {
        if(id != 1) {
            return new ArrayList<Position>() {{
                add(new Position(0,0));
                add(new Position(enemey_teritory.x-1, enemey_teritory.y-1) );
            }};
        } else {
            return new ArrayList<Position>() {
                {
                    add(new Position(enemey_teritory.x, 0));
                    add(new Position(enemey_teritory.y-1, enemey_teritory.y-1));
                }};
        }
    }

    void setEnimeyTeritory(int x, int y){
        enemey_teritory = new Position(x, y);
    }
}

class World {
    int n;
    int[][] map;

    World(int n) {
        this.n = n;
        this.map = new int[n][n];
    }

    boolean initMapWithPlayer(int noOfPlayer) {
        int player = 1;
        System.out.println("initializing players");
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j++) {
                if(i == n/2) player=2;
                map[i][j] = player;
            }
        }
        return true;
    }
}

class Game {
    Player playerA ;
    Player playerB ;
    World world;
    int wsize;

    public boolean initGame(int n) {
        this.wsize = n;
        this.world = new World(n);
        playerA = new Player(1, n, "A");
        playerB = new Player(2, n, "B");
        world.initMapWithPlayer(2);

        playerA.setEnPos(world.map);
        playerB.setEnPos(world.map);

        playerA.setEnimeyTeritory(wsize/2, wsize);
        playerB.setEnimeyTeritory(wsize/2, wsize);
        return true;
    }

    void addShip(int id, int size, int x_player_A, int y_player_A, int x_player_B, int y_player_B) {
        Position posA = new Position(x_player_A, y_player_A);
        Position posB = new Position(x_player_B, y_player_B);

        boolean isPresent = false;
        for(int i=x_player_A; i <=x_player_A+size-1; i++) {
            for(int j=y_player_A; j <=y_player_A+size-1; j++) {
                if(world.map[i][j] == -1) {
                    isPresent = true;
                    break;
                }
                playerA.ships.put(new Position(i, j), id);
                world.map[i][j] = -1;
            }
        }
        if(isPresent)  System.out.println("Not able to ALlocatate ship for Player A");

        isPresent = false;
        for(int i=x_player_B; i <=x_player_B+size-1; i++) {
            for(int j=y_player_B; j <=y_player_B+size-1; j++) {
                if(world.map[i][j] == -2) {
                    isPresent = true;
                    break;
                }
                playerB.ships.put(new Position(i, j), id);
                world.map[i][j] = -2;
            }
        }
        if(isPresent)  System.out.println("Not able to ALlocatate ship for Player B");

        playerA.setEnPos(world.map);
        playerB.setEnPos(world.map);
    }

    void viewBattleField() {
        for(int i = 0; i < wsize; i++) {
            for(int j = 0; j < wsize; j++) {
                System.out.print(world.map[i][j] + " ");
            }
            System.out.println();
        }

    }


    int startGame() {
        Player player = playerA;
        while(true) {
            boolean[][] entr = player.getEnPos();
            List<Position> en = player.getEnTeri(); // n/2 -> n

            outerLoop:
            for(int i=en.get(0).x; i <= en.get(1).x; i++) {
                for(int j=en.get(0).y; j <= en.get(1).y; j++) {

                    if(!entr[i][j]) continue;
                    else {
                        Position position = new Position(i,j);
                        boolean ans = allocatePosition(position, player);
                        player.enemey_map[i][j] = false;
                        if(ans) {
                            if(checkGameStatus(player))
                            {
                                return player.id;
                            }
                        }
                        break outerLoop;
                    }
                }
            }

            if(player == playerA) {
                player = playerB;
            }
            else if(player == playerB) {
                player = playerA;
            }
        }

    }

    private boolean checkGameStatus(Player p) {
        Player opp = null;
        if (p.id == 1) opp = playerB;
        else opp = playerA;

        if(opp.ships.size() == 0) return true;
        else return false;
    }

    private boolean allocatePosition(Position position, Player p) {
        Player opp = (p.id == 1) ? playerB : playerA;
        // ship exists
        if(world.map[position.x][position.y] < 0 && Math.abs(world.map[position.x][position.y]) == opp.id) {

            world.map[position.x][position.y] = 0;
            opp.ships.remove(new Position(position.x, position.y));

            return true;
        }
        return false;
    }

}

public class BattleShip {

    public static void main(String[] args) {
        Game game = new Game();
        game.initGame(6);

        System.out.println("Visualizing Battle Field");
        game.viewBattleField();
        System.out.println("Visualizing Battle Field");

        game.addShip(12312, 2, 1,3 , 4,3);

        System.out.println("Visualizing Battle Field");
        game.viewBattleField();
        System.out.println("Visualizing Battle Field");

        System.out.println(game.startGame());
    }

}
