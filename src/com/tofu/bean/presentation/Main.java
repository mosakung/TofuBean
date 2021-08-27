package com.tofu.bean.presentation;
import com.tofu.bean.mariadb.contract.RepositoryMineCraft;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.mariadb.impl.RepositoryMineCraftImpl;
import com.tofu.bean.mariadb.impl.db.mysql.JavaMySqlImpl;
import com.tofu.bean.plugin.money.MoneyModuleCommand;
import com.tofu.bean.plugin.teleport.action.contract.TeleportAction;
import com.tofu.bean.plugin.teleport.action.impl.TeleportActionImpl;
import com.tofu.bean.plugin.teleport.command.TeleportExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public class Main extends JavaPlugin {

    private final RepositoryMineCraft repo = new RepositoryMineCraftImpl();
    private final JavaMySql db = repo.dbProvider();

    private TeleportAction teleportAction = new TeleportActionImpl();
    private TeleportExecutor teleportExecutor = new TeleportExecutor(teleportAction);
    private final MoneyModuleCommand moneyModuleCommand = new MoneyModuleCommand(db);


    @Override
    public void onEnable() {
        this.getCommand("tpc").setExecutor(teleportExecutor);
        this.getCommand("money").setExecutor(moneyModuleCommand);


        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println("Main : onDisable");
        super.onDisable();
    }
}
