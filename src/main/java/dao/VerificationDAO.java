package dao;

import entities.Verification;

public interface VerificationDAO {
	void saveVerification(Verification verification);
    Verification findByEmail(String email);
    void updatePassword(String email, String newPassword);
}
