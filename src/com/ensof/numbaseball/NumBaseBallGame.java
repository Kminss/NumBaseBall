package com.ensof.numbaseball;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

//게임 관련 기능 ex 점수 결산, 게임 설정 등, start 등
public class NumBaseBallGame {
    private final int DEFAULT_BALL_LENGTH = 3; //게임 기본 자리 수
    private final String DONE_MESSAGE = "DONE"; //정답 메세지
    private NumBaseBall numbaseBall;
    
    public void setNumBaseBall(int length) {
        try {
            numbaseBall = new NumBaseBall(length);
        } catch (IllegalArgumentException e) {
            numbaseBall = new NumBaseBall(DEFAULT_BALL_LENGTH);
        }
        numbaseBall.generateNumBall();
    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=======================숫자 야구게임========================");
        System.out.print("게임 자리수 설정 값 입력 (3자리 이상 9자리 이하>>>>>>>");

        int length = sc.nextInt();
        setNumBaseBall(length);
        System.out.println("정답 생성....");

        while(true) {
            System.out.print("정답 입력>");
            int[] input;
            try {
                int num = sc.nextInt();
                input = Stream.of(String.valueOf(num).split("")).mapToInt(Integer::parseInt).toArray();
            } catch (InputMismatchException e) {
                System.out.println("다시 입력해주세요");
                sc.nextLine();
                continue;
            }
            String result = numbaseBall.makeScore(input);
            if(result.endsWith(DONE_MESSAGE)) {
                System.out.println("정답입니다.");
                break;
            }
            System.out.println(result);
        }
        System.out.println("재시작 하시겠습니까? y/any");
        String str = sc.next();
        if("y".equalsIgnoreCase(str)) {
            this.start();
        }
        System.out.println("게임을 종료합니다.");
    }

}
