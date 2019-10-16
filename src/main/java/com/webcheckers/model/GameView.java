package com.webcheckers.model;

import com.webcheckers.model.BoardView;
import com.webcheckers.util.Message;

import java.util.Map;

public class GameView {
//    private Player currentUser;
    private enum viewMode{ PLAY, SPECTATOR, REPLAY}
    private Map<String, Object> modeOptionsAsJSON;
//    private Player redPlayer;
//    private Player whitePlayer;
    private enum activeColor{ RED, WHITE}
    private BoardView board;
    private Message message;

}
