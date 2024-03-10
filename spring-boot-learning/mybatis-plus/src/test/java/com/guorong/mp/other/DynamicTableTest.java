package com.guorong.mp.other;

import com.guorong.mp.entity.DynamicTable;
import com.guorong.mp.mapper.DynamicTableMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class DynamicTableTest {

    @Autowired
    private DynamicTableMapper dynamicTableMapper;


    @Test
    public void test() {
        List<DynamicTable> dynamicTableList = dynamicTableMapper.selectList(null);
        dynamicTableList.forEach(dynamicTable -> log.info(dynamicTable.toString()));
    }

}
