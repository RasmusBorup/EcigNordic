package modelLayer;

public class Person 
{
	private String name;
	private String ssn;
	private String phoneno;
	private String email;
	private String address;
	private String zipCode;
	
	public Person(String name, String ssn, String phoneno, String email, String address, String zipCode)
	{
		this.name = name;
		this.ssn = ssn;
		this.phoneno = phoneno;
		this.email = email;
		this.address = address;
		this.zipCode = zipCode;
	}
	public Person()
	{
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public String getPhoneno() {
		return phoneno;
	}
	
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
