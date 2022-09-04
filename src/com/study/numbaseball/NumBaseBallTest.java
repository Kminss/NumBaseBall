package com.study.numbaseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
class NumBaseBallTest {

    /*
     * 1. 1~9까지의 겹치지 않는 세자리 난수를 생성하고 숨긴다.
     * */
    @DisplayName("중복되지 않는 1~9사이의 세자리 난수 생성")
    @Test
    void testGenerateNumBall() {
        NumBaseBall numBaseBall = new NumBaseBall();
        int[] numBalls = numBaseBall.generateNumBall();
        int[] testBalls = new int[] {1,1,2};
        //numBalls의 길이와 element 중복 제거 후의 길이가 같아야함.
        assertThat(numBalls.length).isEqualTo(Arrays.stream(numBalls).distinct().count());

    }

    @Test
    void makeScore(int[] input) {
        NumBaseBall numBaseBall = new NumBaseBall();

    }
}