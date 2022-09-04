package com.study.numbaseball;

import java.util.Random;
import java.util.stream.IntStream;

public class NumBaseBall {
    private final int[] numBalls;
    private static final int MIN_BOUND_LENGTH = 3;
    private static final int MAX_BOUND_LENGTH = 9;

    //추후 세자리 이상의 게임을 할 경우
    public NumBaseBall() {
        numBalls = new int[3];
    }

    public NumBaseBall(int length)  {
        boolean check = validateLength(length);
        if (!check) {
            throw new IllegalArgumentException("3이상 9이하 외의 값으로 설정 시 기본 값으로 세팅됩니다.");
        }
        numBalls = new int[length];
    }


    //입력받은 게임 자리 수 검증 (3자리 이상 9자리 이하여야 함)
    private boolean validateLength(int length) {
        return length >= MIN_BOUND_LENGTH && length <= MAX_BOUND_LENGTH;
    }

    //중복되지 않는 난수 생성
    public int[] generateNumBall() {
        Random random = new Random();
        for (int i = 0; i < numBalls.length; i++) {
            numBalls[i] = random.nextInt(9) + 1;
            for (int j = 0; j < i; j++) {
                if (numBalls[j] == numBalls[i]) {
                    i--;
                }
            }
        }
        //System.out.println(Arrays.toString(numBalls));
        return numBalls;
    }

    // Ball Count 체크
    private boolean isBall(int answer, int index) {
        int ballIndex = IntStream.range(0, numBalls.length).
                filter(idx -> answer == numBalls[idx]).
                findFirst().orElse(-1);

        //answer,index 값이 numBall 의 value,index 모두 같거나 numBall 에 없는 경우 false return
        return ballIndex != index && ballIndex != -1;
    }

    //Strike Count 체크
    private boolean isStrike(int answer, int index) {
        int ballIndex = IntStream.range(0, numBalls.length).
                filter(idx -> answer == numBalls[idx] && idx == index).
                findFirst().orElse(-1);

        //answer,index 값이 numBall 의 value,index 모두 같은 경우 true return
        return ballIndex == index;
    }

    //정답 체크
    public String makeScore(int[] input) {
        int ballCount = 0;
        int strikeCount = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            if (isBall(input[i], i)) {
                ballCount++;

            } else {
                if (isStrike(input[i], i)) {
                    strikeCount++;
                }
            }
        }

        if(strikeCount > 0) {
            sb.append(strikeCount).append("S ");
        }
        if(ballCount > 0 ) {
            sb.append(ballCount).append("B ");
        }
        if(strikeCount == numBalls.length) {
            sb.append("DONE");
        }
        return sb.toString();
    }
}
