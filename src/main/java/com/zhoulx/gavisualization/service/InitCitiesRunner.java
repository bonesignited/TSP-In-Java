package com.zhoulx.gavisualization.service;


import com.zhoulx.gavisualization.ga.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
@Order(value = 1)
public class InitCitiesRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitCitiesRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("准备初始化城市数据");

        // 从文件中读取城市数据
        String filepath = "src/main/resources/static/data.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
        String line; int lineCount = 0;
        ArrayList<City> temp = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            temp.add(new City(data[0], Double.valueOf(data[1]), Double.valueOf(data[2])));
            lineCount++;
        }
        DataHolder.allCities = new City[lineCount];
        temp.toArray(DataHolder.allCities);

        logger.info("城市数据初始化完成");
    }
}
