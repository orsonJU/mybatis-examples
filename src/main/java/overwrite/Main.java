package overwrite;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		
		final String configuration = "overwrite/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(configuration);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		SqlSession session = factory.openSession();
		AccountMapper mapper = session.getMapper(AccountMapper.class);

		List<Account> accounts = mapper.getAccounts("active");

		accounts.forEach(account -> {
			System.out.println(account.getId());
			System.out.println(account.getName());
			System.out.println(account.getStatus());
			System.out.println(account.getOwner());
		});

		List<Account> accounts2 = mapper.getAccounts("inactive", 1);
		accounts2.forEach(account -> {
			System.out.println(account.getId());
			System.out.println(account.getName());
			System.out.println(account.getStatus());
			System.out.println(account.getOwner());
		});
	}
}
