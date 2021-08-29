package com.tofu.bean.presentation;

import com.tofu.bean.domain.contract.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.SpawnJetBeanInteractor;
import com.tofu.bean.domain.impl.DeadJetBeanInteractorImpl;
import com.tofu.bean.domain.impl.SpawnJetBeanInteractorImpl;
import com.tofu.bean.mariadb.contract.RepositoryMineCraft;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.mariadb.impl.RepositoryMineCraftImpl;
import com.tofu.bean.plugin.beans.BeansTabCompleter;
import com.tofu.bean.plugin.event.TaskModuleEvent;
import com.tofu.bean.plugin.beans.BeansModuleCommand;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.domain.impl.PlayerBeansInteractorImpl;
import com.tofu.bean.plugin.jetbean.JetBeanModuleCommand;
import com.tofu.bean.plugin.jetbean.SpawnJetBeanModuleCommand;
import com.tofu.bean.plugin.jetbean.suggestion.JetBeanTabCompleter;
import com.tofu.bean.plugin.permission.BeanPermissionModuleCommand;
import com.tofu.bean.plugin.permission.BeanPermissionTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin instance;

    // Repository Init //
    private final RepositoryMineCraft repo = new RepositoryMineCraftImpl();
    private final JavaMySql db = repo.dbProvider();

    // Interact Init //
    private final PlayerBeansInteractor playerBeansInteractor = new PlayerBeansInteractorImpl(db);
    private final SpawnJetBeanInteractor spawnJetBeanInteractor = new SpawnJetBeanInteractorImpl(db);
    private final DeadJetBeanInteractor deadJetBeanInteractor = new DeadJetBeanInteractorImpl(db);

    // Command Init //
    private final JetBeanModuleCommand jetBeanModuleCommand = new JetBeanModuleCommand(playerBeansInteractor, deadJetBeanInteractor);
    private final BeansModuleCommand beansModuleCommand = new BeansModuleCommand(playerBeansInteractor);
    private final BeanPermissionModuleCommand beanPermissionModuleCommand = new BeanPermissionModuleCommand();
    private final SpawnJetBeanModuleCommand spawnJetBeanModuleCommand = new SpawnJetBeanModuleCommand(playerBeansInteractor, spawnJetBeanInteractor);

    @Override
    public void onEnable() {
        // Instance //
        instance = this;

        // Command //
        this.getCommand("jetbean").setExecutor(jetBeanModuleCommand);
        this.getCommand("jetbean").setTabCompleter(new JetBeanTabCompleter());

        this.getCommand("beans").setExecutor(beansModuleCommand);
        this.getCommand("beans").setTabCompleter(new BeansTabCompleter());

        this.getCommand("tofu").setExecutor(beanPermissionModuleCommand);
        this.getCommand("tofu").setTabCompleter(new BeanPermissionTabCompleter());

        this.getCommand("spawn").setExecutor(spawnJetBeanModuleCommand);

        // event //
        Bukkit.getPluginManager().registerEvents(new TaskModuleEvent(playerBeansInteractor, deadJetBeanInteractor), this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println("Main : onDisable");
        super.onDisable();
    }

    public static Plugin getInstance() {
        return instance;
    }
}
