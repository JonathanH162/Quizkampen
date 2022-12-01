package se.nackademin;

import org.junit.jupiter.api.Test;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;
import se.nackademin.core.repositories.questionrepository.models.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryServiceTest {
    private QuestionRepositoryService sut = new QuestionRepositoryService();

    @Test
    void should_return_correct_List_of_json_File() {
        List<Category> actual = sut.getCategories("categories.json");
        assertEquals(2, actual.size());
        assertEquals("Historia", actual.get(0).getName());
        assertEquals(5, actual.get(0).getCards().size());
        assertEquals("Vilken historisk person är känd som 'Il Duce'?", actual.get(0).getCards().get(0).getQuestion());
    }
    @Test
    void should_return_correct_List_of_category_name() {
        List<String> actual = sut.getAllCategoriesName();
        assertEquals(2, actual.size());
        assertEquals("Historia", actual.get(0));
        assertEquals("Sport", actual.get(1));
    }

    @Test
    void should_return_correct_questions_from_category_name() {
        List<String> actual = sut.getAllQuestionInCategory("Historia");
        assertEquals(5, actual.size());

    }

    @Test
    void should_return_correct_possibel_answer_for_a_question() {
        List<String> expect = List.of("Dunkerque", "Brest", "Calais", "Le Havre");
        List<String> actual = sut.getAllPossibleAnswers("I vilken fransk stad evakuerades nästan 350.000 allierade soldater under Andra världskriget?");
        assertArrayEquals(expect.toArray(), actual.toArray());

    }

    @Test
    void should_return_correct_answer_for_a_question() {
        String expect = "Röde baronen";
        String actual = sut.getCorrectAnswer("Under vilket namn är Manfred Freiherr von Richthofen bättre känd?");
        assertEquals(expect, actual);

    }



}