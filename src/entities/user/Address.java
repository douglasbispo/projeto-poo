package entities.user;

public class Address {
	private static Integer count = 1;
	
	private Integer idHendereco;
	private String cep;
	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
	
	public Address() {}

	public Address(String cep, String street, Integer number, String neighborhood, String city) {
		this.idHendereco = Address.count;
		this.cep = cep;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.city = city;
		Address.count += 1;
	}

	public Integer getIdHendereco() {
		return idHendereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	};
}
