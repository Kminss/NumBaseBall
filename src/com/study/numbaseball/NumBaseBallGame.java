package com.study.numbaseball;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

//게임 관련 기능 ex 점수 결산, 게임 설정 등, start 등
public class NumBaseBallGame {
    private final int DEFAULT_BALL_LENGTH = 3; //게임 기본 자리 수
    private final String DONE_MESSAGE = "DONE"; //정답 메세지
    private NumBaseBall numbaseBall;

    //정답 난수 생성 후 정답의 자리 수 반환
    public int setNumBaseBall(int length) {
        try {
            numbaseBall = new NumBaseBall(length);
        } catch (IllegalArgumentException e) {
            numbaseBall = new NumBaseBall(DEFAULT_BALL_LENGTH);
            System.out.println(e.getMessage());
        }
        return numbaseBall.generateNumBall().length;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int length;
        int answerLength;
        System.out.println("=======================숫자 야구게임========================");
        //게임 자리수 입력
        while (true) {
            System.out.print("게임 자리수 설정 값 입력 (3자리 이상 9자리 이하)>>>>>>>");
            try {
                length = Integer.parseInt(sc.nextLine());
                answerLength = setNumBaseBall(length);
                System.out.println("정답 생성 중....");
            } catch (NumberFormatException e) {
                System.out.println("다시 입력해주세요!");
                continue;
            }
            break;
        }

        //정답 입력 및 점수 계산
        while (true) {
            System.out.print("정답 입력>");
            int[] input;
            try {
                String answers = sc.nextLine().trim();
                // input = Stream.of(String.valueOf(num).split("")).mapToInt(Integer::parseInt).toArray();
                input = Stream.of(answers.split("")).mapToInt(Integer::parseInt).toArray();
                if (answerLength != input.length) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("다시 입력해주세요!");
                sc.nextLine();
                continue;
            }
            String result = numbaseBall.makeScore(input);
            if (result.endsWith(DONE_MESSAGE)) {
                System.out.println("정답입니다.");
                break;
            }
            System.out.println(result);
        }

        //재시작 여부
        System.out.println("재시작 하시겠습니까? y/any");
        String str = sc.next();
        if ("y".equalsIgnoreCase(str)) {
            start();
        }
        System.out.println("게임을 종료합니다.");
    }
}
