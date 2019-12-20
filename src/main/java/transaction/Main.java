package transaction;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import pojo.Account;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
	
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		final String configuration = "transaction/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(configuration);
		return new SqlSessionFactoryBuilder().build(is);
	}

	public static void main(String[] args) throws Exception {
		// init
		Main app = new Main();
		SqlSessionFactory factory = app.getSqlSessionFactory();

		SqlSessionManager mamager = SqlSessionManager.newInstance(factory);


		// session 1
		{
//			SqlSession session1 = mamager.openSession();
			AccountMapper mapper = mamager.getMapper(AccountMapper.class);

			// 插入一条新的记录
			Account object = new Account(3, "orson3@test.com", "active", 1);
			mapper.saveAccount(object);

			List<Account> accounts = mapper.getAccounts("active");
			accounts.forEach(account -> {
				System.out.println(account.getId());
				System.out.println(account.getName());
				System.out.println(account.getStatus());
				System.out.println(account.getOwner());
			});
			
//			session1.close();
		}
		
		// session 2
		{
			System.out.println("----------------------------------------");
//			SqlSession session2 = mamager.openSession();
			AccountMapper mapper2 = mamager.getMapper(AccountMapper.class);
			List<Account> accounts2 = mapper2.getAccounts("active");
			accounts2.forEach(account -> {
				System.out.println(account.getId());
				System.out.println(account.getName());
				System.out.println(account.getStatus());
				System.out.println(account.getOwner());
			});
		}
	}
}
