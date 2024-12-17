package structra.assignment.task.impl;

import structra.assignment.framework.llm.gen.questions.QuestionGenerationTarget;
import structra.assignment.framework.model.gen.QuizzMaker;
import structra.assignment.framework.model.question.QuestionData;
import structra.assignment.framework.model.question.QuestionType;
import structra.assignment.framework.model.question.base.Question;
import structra.assignment.framework.model.question.concrete.MultiCheckboxQuestion;

public class MultipleChoiceQuestionTarget implements QuestionGenerationTarget{


    @Override
    public String getBasePrompt() {
        return "Generate a multiple-choice question with four answer options.";
    }

    @Override
    public Question<?> parse(String input) {

        QuestionData question =new QuestionData(QuestionType.MULTIPLE_CHOICE);
        return QuizzMaker.createQuestion(question);
    }

    @Override
    public String getTargetContext() {
        return "";
    }
}
