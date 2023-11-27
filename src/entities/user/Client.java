package entities.user;

public class Client extends User {
	private static Integer count = 1;
	
	private Integer idClient;
	private String name;
	private String cpf;
	private Address address;
	
	public Client() {}

	public Client(String name, String cpf, Address address, String email, String password) {
		super(email, password);
		this.idClient = Client.count;
		this.name = name;
		this.cpf = cpf;
		this.address = address;
		Client.count += 1;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return	"ID: " + this.getIdClient() + 
				"\nEmail: " + super.getEmail() + 
				"\nPassword: " + super.getPassword() + 
				"\nNome: " + this.getName() + 
				"\nCPF: " + this.getCpf() + 
				"\n\nEndereço: " + 
				"\nRua: " + address.getStreet() +
				"\nNumero: " + address.getNumber() +
				"\nBairro: " + address.getNeighborhood() +
				"\nCEP: " + address.getCep() +
				"\nCidade: " + address.getCity() + "\n";
	}
}
