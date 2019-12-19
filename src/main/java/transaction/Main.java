package transaction;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		
		final String configuration = "transaction/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(configuration);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		SqlSession session = factory.openSession();
		AccountMapper mapper = session.getMapper(AccountMapper.class);


//		Account object = new Account(3, "orson3@test.com", "active", 1);
//		mapper.saveAccount(object);

		List<Account> accounts = mapper.getAccounts("active");
		accounts.forEach(account -> {
			System.out.println(account.getId());
			System.out.println(account.getName());
			System.out.println(account.getStatus());
			System.out.println(account.getOwner());
		});

	}
}
