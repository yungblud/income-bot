package com.jojoldu.incomebot.parser.parser.book.yes24;

import com.jojoldu.incomebot.parser.parser.book.BookParseResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created by jojoldu@gmail.com on 26/10/2019
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@ToString
@Getter
@RequiredArgsConstructor
public class Yes24ParseResult implements BookParseResult {
    public static final Yes24ParseResult EMPTY = new Yes24ParseResult(0, 0);

    private final long currentScore;
    private final int currentRank;

    @Override
    public String getMessageFormat() {
        return "[예스24] \"{goods}\"의 판매지수가 {addScore} 되어 현재 {newScore} 이며 " +
                "순위가 {addRank} 만큼 {rankIncreaseMode} 하여 {newRank}위를 달성했습니다.";
    }
}
