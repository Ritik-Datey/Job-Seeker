package entities;

import jakarta.persistence.*;

@Entity
public class Verification {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "verification", cascade = CascadeType.ALL)
    private UserDetails userDetails;
    
    public Verification() {
    	
    }
    
	public Verification(String email, String password, UserDetails userDetails) {
		super();
		this.email = email;
		this.password = password;
		this.userDetails = userDetails;
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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
    
	 @Override
	    public String toString() {
	        return "Example{" +
	                "name='" + email + '\'' +
	                ", age=" + password +
	                '}';
	    }

}
