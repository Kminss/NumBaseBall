package com.ensof.numbaseball;

/*
*
수비수(컴퓨터)는 1에서 9까지의 서로 다른 숫자 세 개로 구성된 세 자리 수를 만들고 숨긴다. (예: 324)
공격수(사람)는 1에서 9까지의 서로 다른 숫자 세 개로 구성된 세 자리 수를 입력한다. (예: 123)
공격수가 제시한 세 자리 수에 있는 숫자들 중 하나가 수비수의 세 자리 수의 동일한 자리에 위치하면 스트라이크 한 번으로 세고, 숫자가 세 자리 수에 있긴 하나 다른 자리에 위치하면 볼 한 번으로 센다.
예시
수비수의 수가 3 2 4 일 때,공격수의 수가	4 2 9 는 1 스트라이크 1 볼,
	2 4 1 은 0 스트라이크 2 볼
수비수는 공격수가 제시한 수가 몇 스트라이크, 몇 볼인지 답해준다.
공격수가 수비수의 세 자리 수를 정확하게 맞추어 3 스트라이크가 되면 게임이 끝난다. 아니라면 공격수는 새로운 수를 생각해 다시 입력한다.

* */


import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class NumBaseBall {
    private int[] numBalls;
    private final int MIN_BOUND_LENGTH = 3;
    private final int MAX_BOUND_LENGTH = 9;

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
        if (length < MIN_BOUND_LENGTH || length > MAX_BOUND_LENGTH) {
            return false;
        }
        return true;
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
        if (ballIndex == index || ballIndex == -1) {
            return false;
        }
        return true;
    }

    //Strike Count 체크
    private boolean isStrike(int answer, int index) {
        int ballIndex = IntStream.range(0, numBalls.length).
                filter(idx -> answer == numBalls[idx] && idx == index).
                findFirst().orElse(-1);

        //answer,index 값이 numBall 의 value,index 모두 같은 경우 true return
        if (ballIndex == index) {
            return true;
        }
        return false;
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
            sb.append(strikeCount + "S ");
        }
        if(ballCount > 0 ) {
            sb.append(ballCount + "B ");
        }
        if(strikeCount == numBalls.length) {
            sb.append("DONE");
        }
        return sb.toString();
    }
}
