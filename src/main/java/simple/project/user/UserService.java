package simple.project.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void someMethod() {
        userRepository.someMethod();
    }

    public User insertUser(User user) {
        return userRepository.insertUser(user);
    }

    public String getAccessToken(String code, String state) {
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=1F6dYqHfuH3HZA1vc1Ux";
        apiURL += "&client_secret=XDdYuQObla";
        apiURL += "&redirect_uri=http://localhost:8080/another-class/user/callback";
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                throw new RuntimeException("API 요청과 응답에 실패했습니다. : " + responseCode);
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(res.toString(), Map.class);
            access_token = (String) map.get("access_token");
        } catch (Exception e) {
            e.printStackTrace();
            return access_token;
        }
        return access_token;
    }

    public User userSelectByEmailAndNaverId(APIUserDTO apiUser) {
        return userRepository.userSelectByEmailAndNaverId(apiUser);
    }

    public User getUserByToken(Claims claims) {
        int id = Integer.parseInt(claims.getSubject());
        String email = claims.get("email", String.class);
        return userRepository.selectUserByIdAndEmail(id, email);
    }

    public List<User> findUserById(int id){
        return userRepository.findById(id);
    }
}
