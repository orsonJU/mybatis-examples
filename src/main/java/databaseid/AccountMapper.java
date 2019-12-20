package databaseid;

import java.util.List;
import java.util.Map;

public interface AccountMapper {

	/**
	 * support mysql group_concat function & oracle listagg function
	 * 
	 * @return list of owner and its accounts
	 */
	List<Map<String, Object>> listAccount();
}
