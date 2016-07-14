package services;

import models.Player;

public class PlayerIdentifierServiceImpl implements PlayerIdentifierService {

    private static int counter = 0;

    @Override
    public Player identifiesPlayer() {
        counter++;
        return counter % 2 == 1 ? Player.FIRST : Player.SECOND;
    }
}
