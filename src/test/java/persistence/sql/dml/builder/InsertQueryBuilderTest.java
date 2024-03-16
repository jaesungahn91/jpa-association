package persistence.sql.dml.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.dml.NotEntityPerson;
import domain.dml.Person;
import persistence.sql.dml.builder.InsertQueryBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InsertQueryBuilderTest {

    @Test
    @DisplayName("Person 객체를 이용한 DML INSERT 생성 테스트")
    void DMLInsertTest() {
        // given
        String expectedQuery = "INSERT INTO users (nick_name, old, email) VALUES ('Jamie', 34, 'jaesungahn91@gmail.com');";
        Person person = new Person("Jamie", 34, "jaesungahn91@gmail.com");
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder(person);

        // when
        String actualQuery = insertQueryBuilder.build();

        // then
        assertThat(actualQuery).isEqualTo(expectedQuery);
    }

    @Test
    @DisplayName("@Entity 어노테이션이 없는 NotEntityPerson 객체를 이용한 DML INSERT 생성 실패 테스트")
    void DMLInsertFailTest() {
        // given
        NotEntityPerson notEntityPerson = new NotEntityPerson("Jamie", 34, "jaesungahn91@gmail.com");
        String message = "Does not have an @Entity annotation.";

        // when & then
        assertThatThrownBy(() -> new InsertQueryBuilder(notEntityPerson))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

}
