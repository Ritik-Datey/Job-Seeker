package entities;

import jakarta.persistence.*;

@Entity
public class Verification {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

 
    public Verification() {
    	
    }
    
	public Verification(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
	 @Override
	    public String toString() {
	        return "Example{" +
	                "name='" + email + '\'' +
	                ", password=" + password +
	                '}';
	    }

}
