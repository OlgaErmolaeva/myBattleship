package services;

import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerIdentifierServiceImpl implements PlayerIdentifierService {

    private static int counter = 0;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Override
    public Player identifiesPlayer() {
        counter++;
        Player player = counter % 2 == 1 ? Player.FIRST : Player.SECOND;
        actualPlayerService.initializePlayer(player);
        return player;
    }
}
