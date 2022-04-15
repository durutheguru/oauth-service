package com.julianduru.oauthservice;

import com.julianduru.oauthservice.config.TestDataSourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@Import({
	TestDataSourceConfig.class
})
public class OauthServiceApplicationTests {


	@Test
	public void contextLoads() {
	}



}
