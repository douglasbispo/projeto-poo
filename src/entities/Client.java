package entities;

public class Client extends User {
	private static Integer count = 1;
	
	private Integer idClient;
	private String name;
	private String cpf;
	private String adress;
	
	public Client() {}

	public Client(String name, String cpf, String adress) {
		this.idClient = Client.count;
		this.name = name;
		this.cpf = cpf;
		this.adress = adress;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		return	"ID: " + this.getIdClient() + 
				"\nNome: " + this.getName() + 
				"\nCPF: " + this.getCpf() + 
				"\nEndereço: " + this.getAdress() + "\n";
	}
}
