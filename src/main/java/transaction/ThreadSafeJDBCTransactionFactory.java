package transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.sql.Connection;

public class ThreadSafeJDBCTransactionFactory implements TransactionFactory {
	
	ThreadLocal<JdbcTransaction> local = new ThreadLocal<>();

	@Override
	public Transaction newTransaction(Connection conn) {
		JdbcTransaction tnx = local.get();
		if(tnx == null) {
			local.set(new JdbcTransaction(conn));
			tnx = local.get();
		}
		return tnx;
	}

	@Override
	public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
		JdbcTransaction tnx = local.get();
		if(tnx == null) {
			local.set(new JdbcTransaction(dataSource, level, autoCommit));
			tnx = local.get();
		}
		return tnx;
	}
}
