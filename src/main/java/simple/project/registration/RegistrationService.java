package simple.project.registration;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public boolean register(int userId, int courseId) {
        if (!registrationRepository.findRegistration(userId, courseId)) {
            return false;
        }
        registrationRepository.register(userId, courseId);
        return true;
    }

    public void adminRegister(int userId, int courseId) {
        registrationRepository.adminRegister(userId, courseId);
    }
}
