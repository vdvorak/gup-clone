package ua.com.itproekt.gup.model;

public class RestCustomer {
	private Long     id;
	private String name;
	
	public RestCustomer() {}
	
	public RestCustomer(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return "RestCustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
