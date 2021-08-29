package com.tofu.bean.presentation;

import com.tofu.bean.domain.contract.SpawnBeanInteractor;
import com.tofu.bean.domain.impl.SpawnBeanInteractorImpl;
import com.tofu.bean.mariadb.contract.RepositoryMineCraft;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.mariadb.impl.RepositoryMineCraftImpl;
import com.tofu.bean.plugin.event.TaskModuleEvent;
import com.tofu.bean.plugin.beans.BeansModuleCommand;
import com.tofu.bean.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.domain.impl.PlayerBeansInteractorImpl;
import com.tofu.bean.plugin.jetbean.JetBeanModuleCommand;
import com.tofu.bean.plugin.jetbean.SpawnBeanModuleCommand;
import com.tofu.bean.plugin.permission.BeanPermissionModuleCommand;
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
    private final SpawnBeanInteractor spawnBeanInteractor = new SpawnBeanInteractorImpl(db);

    // Command Init //
    private final JetBeanModuleCommand jetBeanModuleCommand = new JetBeanModuleCommand(playerBeansInteractor);
    private final BeansModuleCommand beansModuleCommand = new BeansModuleCommand(playerBeansInteractor);
    private final BeanPermissionModuleCommand beanPermissionModuleCommand = new BeanPermissionModuleCommand();
    private final SpawnBeanModuleCommand spawnBeanModuleCommand = new SpawnBeanModuleCommand(playerBeansInteractor, spawnBeanInteractor);

    @Override
    public void onEnable() {
        // Instance //
        instance = this;

        // Command //
        this.getCommand("jetbean").setExecutor(jetBeanModuleCommand);
        this.getCommand("beans").setExecutor(beansModuleCommand);
        this.getCommand("tofu").setExecutor(beanPermissionModuleCommand);
        this.getCommand("spawn").setExecutor(spawnBeanModuleCommand);

        // event //
        Bukkit.getPluginManager().registerEvents(new TaskModuleEvent(playerBeansInteractor), this);

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
