package simple.project.registration;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void register(int userId, int courseId) {
        registrationRepository.register(userId, courseId);
    }

    public void adminRegister(int userId, int courseId) {
        registrationRepository.adminRegister(userId, courseId);
    }
}
