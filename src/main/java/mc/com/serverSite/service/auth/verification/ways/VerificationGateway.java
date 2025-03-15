package mc.com.serverSite.service.auth.verification.ways;

public interface VerificationGateway {

    /**

     * @param recipient Receiver (email or phone number)
     * */
    void sendVerification(String recipient, String otpCode);

}
