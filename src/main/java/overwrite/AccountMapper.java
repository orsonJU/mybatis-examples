package overwrite;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
	/**
	 * get accounts according to status
	 *
	 * @param status active or inactive
	 * @return list of accounts
	 */
	default List<Account> getAccounts(@Param("status") String status) {
		return this.getAccounts(status, -1);
	}

	/**
	 * get accounts according to status and owner
	 *
	 * @param status active or inactive
	 * @param owner  owner id
	 * @return list of accounts
	 */
	List<Account> getAccounts(@Param("status") String status, @Param("owner") int owner);
}
