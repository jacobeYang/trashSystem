package com.jacob.trash;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// mapper接口扫描包配置
@MapperScan({"com.jacob.trash.dao"})
//开启事务管理
@EnableTransactionManagement
//开启定时任务
@EnableScheduling
public class TrashApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrashApplication.class, args);
	}

}
