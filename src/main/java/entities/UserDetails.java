package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_details")
public class UserDetails {

	    @Id
	    @Column(nullable = false, unique = true)
	    private String email; // Email as the primary key

	    @Column(nullable = false)
	    private String prefix;

	    @Column(nullable = false)
	    private String firstName;

	    private String middleName;

	    @Column(nullable = false)
	    private String lastName;

	    @Column(nullable = false)
	    private String gender;

	    @Column(nullable = false)
	    private String phone;

	    @Column(nullable = false)
	    private String qualification;

	    @Column(nullable = false)
	    private String fieldOfStudy;

	    @Column(nullable = false)
	    private String position;

	    @Column(nullable = false)
	    private String experience;
	    
	    @Column(nullable = false)
	    private String skills;

	    @Lob
	    private String coverLetter;

	    @Lob
	    @Column(nullable = false)
	    private String resumePath; // Path to store uploaded file on the server
	    
	    // One-to-One relationship with Verification (parent)
	    @OneToOne
	    @MapsId
	    @JoinColumn(name = "email")
	    private Verification verification;
	  
	    public UserDetails() {
	    	
	    }
	    
		public UserDetails(String email, String prefix, String firstName, String middleName, String lastName,
				String gender, String phone, String qualification, String fieldOfStudy, String position,
				String experience, String skills, String coverLetter, String resumePath, Verification verification) {
			super();
			this.email = email;
			this.prefix = prefix;
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
			this.gender = gender;
			this.phone = phone;
			this.qualification = qualification;
			this.fieldOfStudy = fieldOfStudy;
			this.position = position;
			this.experience = experience;
			this.skills = skills;
			this.coverLetter = coverLetter;
			this.resumePath = resumePath;
			this.verification = verification;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getQualification() {
			return qualification;
		}

		public void setQualification(String qualification) {
			this.qualification = qualification;
		}

		public String getFieldOfStudy() {
			return fieldOfStudy;
		}

		public void setFieldOfStudy(String fieldOfStudy) {
			this.fieldOfStudy = fieldOfStudy;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getExperience() {
			return experience;
		}

		public void setExperience(String experience) {
			this.experience = experience;
		}

		public String getSkills() {
			return skills;
		}

		public void setSkills(String skills) {
			this.skills = skills;
		}

		public String getCoverLetter() {
			return coverLetter;
		}

		public void setCoverLetter(String coverLetter) {
			this.coverLetter = coverLetter;
		}

		public String getResumePath() {
			return resumePath;
		}

		public void setResumePath(String resumePath) {
			this.resumePath = resumePath;
		}

		public Verification getVerification() {
			return verification;
		}

		public void setVerification(Verification verification) {
			this.verification = verification;
		}

		@Override
		public String toString() {
			return "UserDetails [email=" + email + ", prefix=" + prefix + ", firstName=" + firstName + ", middleName="
					+ middleName + ", lastName=" + lastName + ", gender=" + gender + ", phone=" + phone
					+ ", qualification=" + qualification + ", fieldOfStudy=" + fieldOfStudy + ", position=" + position
					+ ", experience=" + experience + ", skills=" + skills + ", coverLetter=" + coverLetter
					+ ", resumePath=" + resumePath + ", verification=" + verification + "]";
		}

}
