package com.jojoldu.incomebot.batch.job.notify.parser.online.inflearn;

import com.jojoldu.incomebot.batch.job.notify.parser.LectureParser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

import static java.lang.Long.parseLong;

/**
 * Created by jojoldu@gmail.com on 12/10/2019
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@Slf4j
public class InflearnParser implements LectureParser<InflearnParseResult> {
    @Override
    public InflearnParseResult parse(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            return new InflearnParseResult(getStudentCount(document), getCoursePrice(document));
        } catch (IOException e) {
            log.error("인프런 URL 파싱에 실패하였습니다.");
        }
        return InflearnParseResult.EMPTY;
    }

    private long getStudentCount(Document document) {
        Elements elements = document.select(".student_cnt");
        if (CollectionUtils.isEmpty(elements)) {
            return 0;
        }

        Element section = elements.get(0);
        String content = section.text();
        return parseLong(content.replaceAll("\\D+", ""));
    }

    private long getCoursePrice(Document document) {
        Elements elements = document.select(".course_price");
        if (CollectionUtils.isEmpty(elements)) {
            return 0;
        }

        Element section = elements.get(0);
        String content = section.text();
        return parseLong(content.replaceAll("\\D+", ""));
    }
}
