package overwrite;


/**
 * Account object
 */
public class Account {
	
	private int id;
	private String name;
	private String status;
	private int owner;

	public Account(int id, String name, String status, int owner) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
}
