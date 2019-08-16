package com.jacob.trash;

import com.jacob.trash.dao.RoleMapper;
import com.jacob.trash.redis.RedisService;
import com.jacob.trash.service.TrashTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrashApplicationTests {
	@Autowired
	RedisService redisService;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
    TrashTypeService trashTypeService;

	@Test
	public void contextLoads() {
//		redisService.LSet("roleList",roleMapper.selectAll());
//		System.out.println("============"+redisService.LGet("roleList",0L,4L));
//		Type a[] = trashTypeService.getTypeValues();
//		System.out.println(a[0]);


	}

}
