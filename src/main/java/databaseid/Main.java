package databaseid;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Main {


	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		final String configuration = "databaseid/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(configuration);
		return new SqlSessionFactoryBuilder().build(is);
	}

	public static void main(String[] args) throws IOException {
		// init
		Main app = new Main();
		SqlSessionFactory factory = app.getSqlSessionFactory();

		// get session
		SqlSession session = factory.openSession();
		AccountMapper mapper = session.getMapper(AccountMapper.class);

		List<Map<String, Object>> maps = mapper.listAccount();
		for(Map<String, Object> map : maps) {
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("key: " + entry.getKey());
				System.out.println("value: " + entry.getValue());
			}
			System.out.println("--------------------------");
		}
		
		
	}
}
