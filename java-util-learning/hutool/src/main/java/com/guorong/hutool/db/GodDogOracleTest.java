package com.guorong.hutool.db;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

class GodDogOracleTest {

    private DataSource dataSource = DSFactory.get("group_goddog_oracle");

    @Test
    void select() throws SQLException {
        Entity where = Entity.create("AREA_CODE_2022");
        List<Entity> entityList = Db.use(dataSource).find(where);
        System.out.println(entityList.size());
    }


}
