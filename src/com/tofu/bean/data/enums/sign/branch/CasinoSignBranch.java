package com.tofu.bean.data.enums.sign.branch;

import com.tofu.bean.data.enums.casino.table.CasinoGameType;
import com.tofu.bean.data.enums.sign.ActionSign;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static com.tofu.bean.data.enums.casino.table.CasinoGameType.*;
import static com.tofu.bean.data.enums.sign.ActionSign.CASINO;
import static com.tofu.bean.utils.sign.CasinoSignMessageUtils.*;

public enum CasinoSignBranch {
    BJ_250_TABLE(
            CASINO,
            "black_jack_250_table",
            "Black Jack XXS",
            BLACK_JACK,
            999,
            1,
            250.0,
            "#bj:250"
    ),
    BJ_500_TABLE(
            CASINO,
            "black_jack_500_table",
            "Black Jack XS",
            BLACK_JACK,
            999,
            1,
            500.0,
            "#bj:500"
    ),
    BJ_1000_TABLE(
            CASINO,
            "black_jack_1000_table",
            "Black Jack SS",
            BLACK_JACK,
            999,
            1,
            1000.0,
            "#bj:1000"
    ),
    BJ_2500_TABLE(
            CASINO,
            "black_jack_2500_table",
            "Black Jack S",
            BLACK_JACK,
            999,
            1,
            2500.0,
            "#bj:2500"),
    BJ_5000_TABLE(
            CASINO,
            "black_jack_5000_table",
            "Black Jack M",
            BLACK_JACK,
            999,
            1,
            5000.0,
            "#bj:5000"
    ),
    BJ_10000_TABLE(
            CASINO,
            "black_jack_10000_table",
            "Black Jack L",
            BLACK_JACK,
            999,
            1,
            10000.0,
            "#bj:10000"
    );

    private final ActionSign actionSign;
    private final String tableName;
    private final String tableNameLabel;
    private final CasinoGameType casinoGameType;
    private final int maxPlayer;
    private final int minimumPlayer;
    private final Double bet;
    private final String tracking;

    private final String messageTopic;
    private final String messageTableNameLabel;
    private final String messageRule;
    private final String messageBet;

    private final String messageInGameTableName;

    CasinoSignBranch(
            ActionSign actionSign,
            String tableName,
            String tableNameLabel,
            CasinoGameType casinoGameType,
            int maxPlayer,
            int minimumPlayer,
            Double bet,
            String tracking
    ) {
        this.actionSign = actionSign;
        this.tableName = tableName;
        this.tableNameLabel = tableNameLabel;
        this.casinoGameType = casinoGameType;
        this.maxPlayer = maxPlayer;
        this.minimumPlayer = minimumPlayer;
        this.bet = bet;
        this.tracking = tracking;

        this.messageTopic = actionSign.getActionMessage();
        this.messageTableNameLabel = buildCasinoMessageTableNameLabel(this.tableNameLabel);
        this.messageRule = buildCasinoMessageRule(this.maxPlayer, this.minimumPlayer);
        this.messageBet = buildCasinoMessageMessageBet(this.bet);

        this.messageInGameTableName = ChatColor.GOLD + this.tableNameLabel;
    }

    public ActionSign getActionSign() {
        return actionSign;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableNameLabel() {
        return tableNameLabel;
    }

    public CasinoGameType getCasinoTableType() {
        return casinoGameType;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public int getMinimumPlayer() {
        return minimumPlayer;
    }

    public Double getBet() {
        return bet;
    }

    public String getTracking() {
        return tracking;
    }

    public String getMessageTopic() {
        return messageTopic;
    }

    public String getMessageTableNameLabel() {
        return messageTableNameLabel;
    }

    public String getMessageRule() {
        return messageRule;
    }

    public String getMessageBet() {
        return messageBet;
    }

    public String getMessageInGameTableName(String playerName) {
        return messageInGameTableName + ChatColor.WHITE + " : " + playerName;
    }

    public Boolean equal(String messageTableNameLabel, String messageRule, String messageBet) {
        return this.messageTableNameLabel.equals(messageTableNameLabel) && this.messageRule.equals(messageRule) && this.messageBet.equals(messageBet);
    }

    public static Boolean isCasinoSignBranch(String tableNameLabel) {
        AtomicReference<Boolean> result = new AtomicReference<>(false);

        Arrays.stream(CasinoSignBranch.values()).forEach(casinoSignBranch -> {
            if (casinoSignBranch.getTableNameLabel().equals(tableNameLabel)) {
                result.set(true);
            }
        });

        return result.get();
    }

    public static CasinoSignBranch getCasinoSignBranchByTableName(String tableName) {
        AtomicReference<CasinoSignBranch> result = new AtomicReference<>(null);

        Arrays.stream(CasinoSignBranch.values()).forEach(signCasinoTableMethod -> {

            if (signCasinoTableMethod.getTableName().equals(tableName)) {
                result.set(signCasinoTableMethod);
            }
        });

        return result.get();
    }

    public static CasinoSignBranch getCasinoSignBranch(String tracking) {
        AtomicReference<CasinoSignBranch> result = new AtomicReference<>(null);

        Arrays.stream(CasinoSignBranch.values()).forEach(signCasinoTableMethod -> {

            if (signCasinoTableMethod.getTracking().equals(tracking)) {
                result.set(signCasinoTableMethod);
            }
        });

        return result.get();
    }

    public static CasinoSignBranch getCasinoSignBranch(String messageTableNameLabel, String messageRule, String messageBet) {
        AtomicReference<CasinoSignBranch> result = new AtomicReference<>(null);

        Arrays.stream(CasinoSignBranch.values()).forEach(signCasinoTableMethod -> {

            if (signCasinoTableMethod.equal(messageTableNameLabel, messageRule, messageBet)) {
                result.set(signCasinoTableMethod);
            }
        });

        return result.get();
    }

    public static CasinoSignBranch findCasinoSignBranch(String inventoryName, String playerName) {
        AtomicReference<CasinoSignBranch> _casinoSignBranch = new AtomicReference<>(null);

        Arrays.stream(CasinoSignBranch.values()).forEach(casinoSignBranch -> {
            if (ChatColor.stripColor(inventoryName).equals(ChatColor.stripColor(casinoSignBranch.getMessageInGameTableName(playerName)))) {
                _casinoSignBranch.set(casinoSignBranch);
            }
        });

        return _casinoSignBranch.get();
    }
}
