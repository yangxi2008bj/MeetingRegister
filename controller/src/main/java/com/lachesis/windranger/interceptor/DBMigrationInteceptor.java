package com.lachesis.windranger.interceptor;

import javax.annotation.PostConstruct;

import org.flywaydb.core.Flyway;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

public class DBMigrationInteceptor {
    private Log log = LogFactory.getLog(DBMigrationInteceptor.class);  
    private Flyway flyway;  
    
    @PostConstruct  
    public void run() {  
        log.info("[Start] DbMigration run .. ");
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();   
        log.info("[End] DbMigration run .. ");  
    }  
    public void setFlyway(Flyway flyway) {  
        this.flyway = flyway;  
    }  
}
