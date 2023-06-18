package simple.project.registration;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Registration> findByCourseId(int course_id){
        return registrationRepository.findRegByCourseId(course_id);
    }
    public void updateReg(int regId){registrationRepository.updateReg(regId);}
    public void deleteReg(int regId){registrationRepository.deleteReg(regId);}
    public List<Registration> findAttendListByCourseId(int courseId){
        return registrationRepository.findAttendListByCourseId(courseId);
    }

}
