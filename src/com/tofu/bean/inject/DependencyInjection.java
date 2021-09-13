package com.tofu.bean.inject;

import com.tofu.bean.domain.contract.beans.PlayerBeansInteractor;
import com.tofu.bean.domain.contract.jetbean.DeadJetBeanInteractor;
import com.tofu.bean.domain.contract.jetbean.SpawnJetBeanInteractor;
import com.tofu.bean.domain.impl.beans.PlayerBeansInteractorImpl;
import com.tofu.bean.domain.impl.jetbean.DeadJetBeanInteractorImpl;
import com.tofu.bean.domain.impl.jetbean.SpawnJetBeanInteractorImpl;
import com.tofu.bean.inject.dependency.*;
import com.tofu.bean.mariadb.contract.db.mysql.JavaMySql;
import com.tofu.bean.presentation.PresentationPlugin;

public class DependencyInjection {

    // Dependency //
    private final JetBeanDependency jetBeanDependency;
    private final BeansDependency beansDependency;
    private final PermissionDependency permissionDependency;
    private final EvokeDependency evokeDependency;
    private final IncomeDependency incomeDependency;
    private final SignDependency signDependency;
    private final FoodDependency foodDependency;
    private final RuleDependency ruleDependency;
    private final CasinoDependency casinoDependency;

    public DependencyInjection(
            JavaMySql db,
            PresentationPlugin presentationPlugin
    ) {
        // Interaction //
        PlayerBeansInteractor playerBeansInteractor = new PlayerBeansInteractorImpl(db);
        SpawnJetBeanInteractor spawnJetBeanInteractor = new SpawnJetBeanInteractorImpl(db);
        DeadJetBeanInteractor deadJetBeanInteractor = new DeadJetBeanInteractorImpl(db);

        this.jetBeanDependency = new JetBeanDependency(presentationPlugin, playerBeansInteractor, spawnJetBeanInteractor, deadJetBeanInteractor);
        this.beansDependency = new BeansDependency(presentationPlugin, playerBeansInteractor);
        this.permissionDependency = new PermissionDependency(presentationPlugin);
        this.evokeDependency = new EvokeDependency(presentationPlugin, playerBeansInteractor);
        this.incomeDependency = new IncomeDependency(presentationPlugin, playerBeansInteractor);
        this.signDependency = new SignDependency(presentationPlugin, playerBeansInteractor);
        this.foodDependency = new FoodDependency(presentationPlugin);
        this.ruleDependency = new RuleDependency(presentationPlugin, playerBeansInteractor);
        this.casinoDependency = new CasinoDependency(presentationPlugin, playerBeansInteractor);
    }

    public void inject() {
        jetBeanDependency.initialize();
        beansDependency.initialize();
        permissionDependency.initialize();
        evokeDependency.initialize();
        incomeDependency.initialize();
        signDependency.initialize();
        foodDependency.initialize();
        ruleDependency.initialize();
        casinoDependency.initialize();
    }
}
