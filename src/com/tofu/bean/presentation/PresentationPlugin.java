package com.tofu.bean.presentation;

import com.tofu.bean.inject.DependencyInjection;
import com.tofu.bean.mariadb.contract.RepositoryMineCraft;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.mariadb.impl.RepositoryMineCraftImpl;
import com.tofu.bean.migration.MigrationModule;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PresentationPlugin extends JavaPlugin {

    public static Plugin instance;

    // Repository Init //
    private final RepositoryMineCraft repo = new RepositoryMineCraftImpl();
    private final JavaMySql db = repo.dbProvider();

    // Migration Init //
    private final MigrationModule migrationModule = new MigrationModule(db);

    // Inject Init //
    private final DependencyInjection dependencyInjection = new DependencyInjection(db, this);

    @Override
    public void onEnable() {
        // Instance //
        instance = this;

        // Migration //
        migrationModule.migrate();

        // Inject //
        dependencyInjection.inject();

        super.onEnable();
    }

    public static Plugin getInstance() {
        return instance;
    }
}
