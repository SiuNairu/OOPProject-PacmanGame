package Logic;

import Map.GameMap;

public class GameEngine {

    private final GameState state;

    private final PacmanSystem pacmanSystem;
    private final GhostSystem ghostSystem;
    private final ItemSystem itemSystem;
    private final RespawnSystem respawnSystem;

    public GameEngine(
            GameState state,
            PacmanSystem pacmanSystem,
            GhostSystem ghostSystem,
            ItemSystem itemSystem,
            RespawnSystem respawnSystem
    ) {
        this.state = state;
        this.pacmanSystem = pacmanSystem;
        this.ghostSystem = ghostSystem;
        this.itemSystem = itemSystem;
        this.respawnSystem = respawnSystem;
    }

    public void update() {
        if (state.isGameOver()) return;

        long now = System.currentTimeMillis();

        pacmanSystem.update(now);
        ghostSystem.update(now);
        itemSystem.update(now);
        respawnSystem.update(now);
        state.getPopups().removeIf(p -> p.isExpired(now));
    }

    public void loadLevel(int level, GameMap map) {
        //Rest all stage
        state.reset();

        // Set level
        state.setCurrentLevel(level);

        // Load map 
        map.loadMap(level, state);
        long now = System.currentTimeMillis();

        // First Cherry after 15s
        state.setNextCherryAtMs(now + 15000);

        // Reset power
        state.deactivatePowerMode();
    }
}
