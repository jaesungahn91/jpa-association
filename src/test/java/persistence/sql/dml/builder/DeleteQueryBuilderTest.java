package persistence.sql.dml.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.dml.Person;
import persistence.sql.dml.builder.DeleteQueryBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteQueryBuilderTest {

    @Test
    @DisplayName("Person 객체를 이용한 DML DELETE 생성 테스트")
    void DMLDeleteTest() {
        // given
        String expectedQuery = "DELETE FROM users WHERE id = 1;";

        // when
        String actualQuery = new DeleteQueryBuilder(Person.class, List.of("id"), List.of(1L)).build();

        // then
        assertThat(actualQuery).isEqualTo(expectedQuery);
    }
}
