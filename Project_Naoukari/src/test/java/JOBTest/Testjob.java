package JOBTest;

import org.example.Dao.JOBDAO;
import org.example.Service.JobService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testjob {

    private JobService jobService;
    private JOBDAO mockJobDao;

    @Test
    void testLogin() {
        String email = "Arru@gmail.com";
        String password = "134567";
        String result = jobService.login(email, password);
        assertEquals("Login successful", result);
    }


}
