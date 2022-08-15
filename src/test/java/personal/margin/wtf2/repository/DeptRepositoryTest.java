package personal.margin.wtf2.repository;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import personal.margin.wtf2.domain.Dept;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptRepositoryTest {

    @Autowired private DeptRepository repository;

    @DisplayName("조회 id에 null 을 사용할 경우")
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findOne() {
        //given
        Long id = null;

        //when
        Dept dept = repository.findOne(id);

        //then
        fail("InvalidDataAccessApiUsageException is expected");
    }
}