package com.tofu.bean.presentation;

import com.tofu.bean.mariadb.contract.RepositoryMineCraft;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.mariadb.impl.RepositoryMineCraftImpl;
import com.tofu.bean.plugin.jobs.event.OnMobDeadEvent;
import com.tofu.bean.plugin.jobs.event.OnPlayerDead;
import com.tofu.bean.plugin.beans.BeansModuleCommand;
import com.tofu.bean.plugin.beans.domain.contract.PlayerBeansInteractor;
import com.tofu.bean.plugin.beans.domain.impl.PlayerBeansInteractorImpl;
import com.tofu.bean.plugin.jetbean.JetBeanModuleCommand;
import com.tofu.bean.plugin.permission.BeanPermissionModuleCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final RepositoryMineCraft repo = new RepositoryMineCraftImpl();
    private final JavaMySql db = repo.dbProvider();

    private final PlayerBeansInteractor playerBeansInteractor = new PlayerBeansInteractorImpl(db);

    private final JetBeanModuleCommand jetBeanModuleCommand = new JetBeanModuleCommand(playerBeansInteractor);
    private final BeansModuleCommand beansModuleCommand = new BeansModuleCommand(playerBeansInteractor);
    private final BeanPermissionModuleCommand beanPermissionModuleCommand = new BeanPermissionModuleCommand(this);

    @Override
    public void onEnable() {
        this.getCommand("jetbean").setExecutor(jetBeanModuleCommand);
        this.getCommand("beans").setExecutor(beansModuleCommand);
        this.getCommand("beanp").setExecutor(beanPermissionModuleCommand);

        Bukkit.getPluginManager().registerEvents(new OnMobDeadEvent(playerBeansInteractor), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerDead(playerBeansInteractor), this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println("Main : onDisable");
        super.onDisable();
    }
}
