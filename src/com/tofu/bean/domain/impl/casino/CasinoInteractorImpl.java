package com.tofu.bean.domain.impl.casino;

import com.tofu.bean.data.methods.casino.card.standard.StandardCardPointMethod;
import com.tofu.bean.data.methods.casino.card.standard.StandardCardSymbolMethod;
import com.tofu.bean.data.methods.sign.branch.CasinoSignBranch;
import com.tofu.bean.data.objects.casino.CasinoCard;
import com.tofu.bean.data.objects.casino.CasinoPlayerHand;
import com.tofu.bean.domain.contract.casino.CasinoInteractor;
import com.tofu.bean.domain.DefaultInteractorImpl;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import oshi.util.tuples.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static com.tofu.bean.utils.CasinoUtils.covertRawtoCasinoPlayerHands;

public class CasinoInteractorImpl extends DefaultInteractorImpl implements CasinoInteractor {

    private final JavaMySql db;

    public CasinoInteractorImpl(JavaMySql db) {
        super(db);
        this.db = db;
    }

    @Override
    public void sitInTable(String playerName, CasinoSignBranch casinoTableMethod, Double betValue) {
        int playerId = getPlayerId(playerName);
        int casinoTableId = getCasinoTableId(casinoTableMethod);

        if (playerId == -1 || casinoTableId == -1) {
            return;
        }

        if (!alreadySitIn(playerId) && !hasFullPlayer(casinoTableId)) {
            int availableSeatOrder = calculateSitInOrder(casinoTableId);

            if (availableSeatOrder == -1) {
                return;
            }

            String updatePlayerSitIn = "UPDATE `user` SET `casino_table_sit` = " + casinoTableId
                    + ", `casino_table_sit_order` = " + availableSeatOrder
                    + ", `casion_table_ready_status` = 0"
                    + ", `casion_table_bet` = " + betValue.toString()
                    + " WHERE `user_id` = " + playerId;

            String[] dataSet = {};

            db.modified(updatePlayerSitIn, dataSet);
        }
    }

    @Override
    public void standUpTable(String playerName) {
        int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        if (alreadySitIn(playerId)) {
            int lobbyId = getCasinoTableId(CasinoSignBranch.LOBBY_TABLE);

            String updatePlayerSitIn = "UPDATE `user` SET `casino_table_sit` = " + lobbyId
                    + ", `casino_table_sit_order` = null"
                    + ", `casion_table_ready_status` = 0"
                    + ", `casion_table_bet` = null"
                    + " WHERE `user_id` = " + playerId;

            String[] dataSet = {};

            db.modified(updatePlayerSitIn, dataSet);
        }
    }

    @Override
    public void callReady(String playerName) {
        int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        if (alreadySitIn(playerId)) {
            String updatePlayerReady = "UPDATE `user` SET `casion_table_ready_status` = 1 WHERE `user_id` = " + playerId;

            String[] dataSet = {};

            db.modified(updatePlayerReady, dataSet);
        }
    }

    @Override
    public void callUnReady(String playerName) {
        int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        if (alreadySitIn(playerId)) {
            String updatePlayerReady = "UPDATE `user` SET `casion_table_ready_status` = 1 WHERE `user_id` = " + playerId;

            String[] dataSet = {};

            db.modified(updatePlayerReady, dataSet);
        }
    }

    @Override
    public void overCardInTable(CasinoCard[] casinoCard, CasinoSignBranch casinoTableMethod) {
        int casinoTableId = getCasinoTableId(casinoTableMethod);

        if (casinoTableId == -1) {
            return;
        }

        Connection connection = db.provideConnection();

        String overCard = "INSERT INTO `casino_card_in_table` (`point`, `symbol`, `casino_table_id`) VALUES (?, ?, ?)";

        String[] points = Arrays.stream(casinoCard).map(cc -> cc.getPoint().getName()).toArray(String[]::new);

        String[] symbols = Arrays.stream(casinoCard).map(cc -> cc.getSymbol().getName()).toArray(String[]::new);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(overCard);
            preparedStatement.setArray(1, connection.createArrayOf("VARCHAR", points));
            preparedStatement.setArray(2, connection.createArrayOf("VARCHAR", symbols));
            preparedStatement.setInt(3, casinoTableId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : overCardInTable : SQLException");
            e.printStackTrace();
        }
    }

    @Override
    public void pushCardInPlayer(String playerName, CasinoCard casinoCard, int handOrder) {
        int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        if (alreadySitIn(playerId)) {
            String insertCard2Player = "INSERT INTO `casino_card_player_hand` (`point`, `symbol`, `hand_order`, `user_id`) VALUES (" + casinoCard.getPoint().getName()
                    + ", " + casinoCard.getSymbol().getName()
                    + ", " + handOrder
                    + ", " + playerId + ")";

            String[] dataSet = {};

            db.insert(insertCard2Player, dataSet);
        }
    }

    @Override
    public CasinoPlayerHand[] viewCardPlayer(String playerName) {
        int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return null;
        }

        String selectPlayerHand = "SELECT `point`, `symbol`, `hand_order` FROM `casino_card_player_hand` WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        ResultSet result = db.fetch(selectPlayerHand, dataSet);

        try {
            List<Pair<Integer, CasinoCard>> preCasinoCard = new ArrayList<>();

            while (result.next()) {
                String point = result.getString("point");
                String symbol = result.getString("symbol");

                CasinoCard casinoCard = new CasinoCard(
                        StandardCardPointMethod.getMethod(point),
                        StandardCardSymbolMethod.getMethod(symbol)
                );

                preCasinoCard.add(new Pair<>(result.getInt("hand_order"), casinoCard));
            }

            if(preCasinoCard.size() == 0) {
                return null;
            }

            return covertRawtoCasinoPlayerHands(preCasinoCard);

        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : viewCardPlayer : SQLException");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void clearCardInPlayer(String[] playerName) {
        Arrays.stream(playerName).forEach(this::clearCardInPlayer);
    }

    @Override
    public void clearCardInPlayer(String playerName) {
        int playerId = getPlayerId(playerName);

        if (playerId == -1) {
            return;
        }

        String deleteCardPlayer = "DELETE FROM `casino_card_player_hand` WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        db.modified(deleteCardPlayer, dataSet);
    }

    @Override
    public Boolean hasPlayerAllReady(CasinoSignBranch casinoTableMethod) {
        int casionTableId = getCasinoTableId(casinoTableMethod);

        if (casionTableId == -1) {
            return null;
        }

        String selectUserInTable = "SELECT `casion_table_ready_status` FROM `user` WHERE `casino_table_sit` = " + casionTableId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectUserInTable, dataSet);

        try {
            AtomicReference<Boolean> isReadyAll = new AtomicReference<>(true);
            List<Boolean> readyStatus = new ArrayList<>();
            while (result.next()) {
                readyStatus.add(result.getInt("casion_table_ready_status") == 1);
            }

            if (readyStatus.size() == 0) {
                return null;
            }

            readyStatus.forEach(ready -> {
                if (!ready) {
                    isReadyAll.set(false);
                }
            });

            return isReadyAll.get();
        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : hasPlayerAllReady : SQLException");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean alreadySitIn(int playerId) {
        String selectPlayer = "SELECT `casino_table_sit` FROM `user` WHERE `user_id` = " + playerId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectPlayer, dataSet);

        try {
            if (result.next()) {
                int casinoTableId = result.getInt("casino_table_sit");
                CasinoSignBranch casinoTableMethod = convert2CasinoTable(casinoTableId);

                return casinoTableMethod != CasinoSignBranch.LOBBY_TABLE;

            }
            return null;

        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : alreadySitIn : SQLException");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean hasFullPlayer(int casinoTableId) {
        int limitPlayer = getLimitPlayerInCasinoTable(casinoTableId);
        int hasPlayer = getNumberPlayerSitInTable(casinoTableId);

        if (hasPlayer == -1 || limitPlayer == -1) {
            return null;
        }

        return hasPlayer < limitPlayer;
    }

    private int getLimitPlayerInCasinoTable(int casinoTableId) {
        String selectCasinoTable = "SELECT `max_player` FROM `casino_table` WHERE `casino_table_id` =" + casinoTableId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectCasinoTable, dataSet);

        try {
            if (result.next()) {
                return result.getInt("max_player");
            }
            return -1;

        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : getLimitPlayerInCasinoTable : SQLException");
            e.printStackTrace();
        }

        return -1;
    }

    private int getNumberPlayerSitInTable(int casinoTableId) {
        String selectNumberPlayerSitInTable = "SELECT COUNT(user_id) AS count_player_sit_in_table FROM `user` WHERE `casino_table_sit` = " + casinoTableId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectNumberPlayerSitInTable, dataSet);

        try {
            if (result.next()) {
                return result.getInt("count_player_sit_in_table");
            }
            return -1;

        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : getNumberPlayerSitInTable : SQLException");
            e.printStackTrace();
        }

        return -1;
    }

    private CasinoSignBranch convert2CasinoTable(int casinoTableId) {
        String selectCasinoTable = "SELECT `name` FROM `casino_table` WHERE `casino_table_id` = " + casinoTableId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectCasinoTable, dataSet);

        try {
            if (result.next()) {
                String tableName = result.getString("name");
                return CasinoSignBranch.getTableMethod(tableName);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : alreadySitIn : SQLException");
            e.printStackTrace();
        }

        return null;
    }

    private int getCasinoTableId(CasinoSignBranch casinoTableMethod) {
        String casinoTableName = casinoTableMethod.getName();
        return getCasinoTableId(casinoTableName);
    }

    private int getCasinoTableId(String casinoTableName) {
        String selectCasinoTable = "SELECT `casino_table_id` FROM `casino_table` WHERE `name` = ?";

        String[] dataSet = {casinoTableName};

        final ResultSet result = db.fetch(selectCasinoTable, dataSet);

        try {
            if (result.next()) {
                return result.getInt("casino_table_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : getCasinoTableId : SQLException");
            e.printStackTrace();
        }

        return -1;
    }

    private int calculateSitInOrder(int casinoTableId) {
        String selectCasinoTable = "SELECT `casino_table_sit_order` FROM `casino_table` WHERE `casino_table_id` = " + casinoTableId;

        String[] dataSet = {};

        final ResultSet result = db.fetch(selectCasinoTable, dataSet);

        try {
            if (result.next()) {
                int limitPlayerTable = getLimitPlayerInCasinoTable(casinoTableId);
                List<Integer> alreadySitInList = new ArrayList();

                while (result.next()) {
                    int orderSit = result.getInt("casino_table_sit_order");
                    alreadySitInList.add(orderSit);
                }

                if (limitPlayerTable == -1) {
                    return -1;
                }

                int[] availableSeats = Arrays.stream(IntStream.rangeClosed(1, limitPlayerTable).toArray()).filter(value -> {
                    return !(alreadySitInList.contains(value));
                }).toArray();

                if (availableSeats.length == 0) {
                    return -1;
                }

                return availableSeats[0];
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("CasinoInteractorImpl : calculateSitInOrder : SQLException");
            e.printStackTrace();
        }

        return -1;
    }
}
