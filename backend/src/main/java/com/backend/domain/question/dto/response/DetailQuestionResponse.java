package com.backend.domain.question.dto.response;

import com.backend.domain.answer.dto.ComplexAnswerResponse;
import com.backend.domain.comment.QuestionComment.dto.QuestionCommentResponse;
import com.backend.domain.comment.dto.AnswerCommentResponse;
import com.backend.domain.comment.dto.SimpleAnswerCommentResponse;
import com.backend.domain.comment.dto.SimpleQuestionCommentResponse;
import com.backend.domain.member.dto.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailQuestionResponse {
    private List<String> tags;

    private MemberResponse member;

    private SimpleQuestionResponse question;

    private List<ComplexAnswerResponse> answers;

    private List<SimpleQuestionCommentResponse> questionComments;



    @Builder
    public DetailQuestionResponse(List<String> tags, MemberResponse member, SimpleQuestionResponse question, List<ComplexAnswerResponse> answers, List<SimpleQuestionCommentResponse> questionComments) {
        this.tags = tags;
        this.member = member;
        this.question = question;
        this.answers = answers;
        this.questionComments = questionComments;

    }
}

/**
 *  질문 1개, 답변 여러개
 * 질문이랑 멤버
 * 질문이랑 태그
 * 질문이랑 답변이랑 댓글 여러개
 */

/**
 * {
 *   "items": [
 *     {
 *       "tags": [
 *         "google-apps-script",
 *         "google-drive-api",
 *         "file-conversion",
 *         "convertapi"
 *       ],
 *       "member": {
 *         "reputation": 23,
 *         "memberId": 11187800,
 *         (*)"profileImage": "https://lh4.googleusercontent.com/-8o1Zs4lQprY/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rebPfyBOjde6DPW0bjTb2M1BSIwCg/mo/photo.jpg?sz=256",
 *         "userName": "fulvio",
 *         "link": "https://stackoverflow.com/users/11187800/fulvio"
 *       },
 *       "question": {
 *         "isAnswered": false,
 *         "viewCount": 2238,
 *         "acceptedAnswerId": 55152707,
 *         "answerCount": 2,
 *         "votes": 2,
 *         (*)"createdAt": 1552344257,
 *         (*)"modifiedAt": 1596034268,
 *         "questionId": 55111503,
 *         "link": "https://stackoverflow.com/questions/55111503/convert-a-gdoc-into-image",
 *         "title": "Convert a gdoc into image",
 *         "content": "An example post body"
 *       },
 *       "qComments": [
 *         {
 *           "qCommnetId": 31187802,
 *           "memberId": 21187802,
 *           "userName": "baptiste",
 *           "link": "https://stackoverflow.com/users/11187800/fulvio",
 *           "content": "An example comment body",
 *           "createdAt": 1552344257,
 *           "modifiedAt": 1596034268
 *         },
 *         {
 *           "qCommnetId": 42187802,
 *           "memberId": 21123802,
 *           "userName": "Entic",
 *           "link": "https://stackoverflow.com/users/11187800/fulvio",
 *           "content": "An example comment body",
 *           "createdAt": 1552234257,
 *           "modifiedAt": 1523034268
 *         }
 *       ],
 *       "answer": [
 *         {
 *           "answerId": 21187802,
 *           "memberId": 21123802,
 *           "createdAt": 1552234257,
 *           "modifiedAt": 1523034268,
 *           "content": "An example body body",
 *           "votes": 2,
 *           "isAccepted": "true",
 *           "reputation": 23,
 *           "profileImage": "https://lh4.googleusercontent.com/-8o1Zs4lQprY/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rebPfyBOjde6DPW0bjTb2M1BSIwCg/mo/photo.jpg?sz=256",
 *           "userName": "fulvio",
 *           "link": "https://stackoverflow.com/users/11187800/fulvio",
 *           "aComments": [
 *             {
 *               "aCommnetId": 31187802,
 *               "memberId": 21187802,
 *               "userName": "baptiste",
 *               "link": "https://stackoverflow.com/users/11187800/fulvio",
 *               "content": "An example comment body",
 *               "createdAt": 1552344257,
 *               "modifiedAt": 1596034268
 *             },
 *             {
 *               "aCommnetId": 42187802,
 *               "memberId": 21123802,
 *               "userName": "Entic",
 *               "link": "https://stackoverflow.com/users/11187800/fulvio",
 *               "content": "An example comment body",
 *               "createdAt": 1552234257,
 *               "modifiedAt": 1523034268
 *             }
 *           ]
 *         }
 *       }
 *     ]
 *   ]
 * }
 */