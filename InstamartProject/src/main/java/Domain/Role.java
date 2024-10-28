package Domain;

public class Role {
	private Integer id;
	private String name;
	private String active;
	
	public Role(Integer id, String name, String active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
}
