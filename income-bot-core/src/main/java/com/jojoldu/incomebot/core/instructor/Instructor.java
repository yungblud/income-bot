package com.jojoldu.incomebot.core.instructor;

/**
 * Created by jojoldu@gmail.com on 12/10/2019
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

import com.jojoldu.incomebot.core.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.jojoldu.incomebot.core.instructor.IntervalType.HOUR_1;

@Getter
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uni_instructor_chat_id", columnNames = {"chatId"}),
        indexes = {
                @Index(name = "idx_instructor_interval", columnList = "idx_instructor_interval")
        }
)
public class Instructor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    private IntervalType interval;

    @Builder
    Instructor(String chatId, IntervalType interval) {
        this.chatId = chatId;
        this.interval = interval;
    }

    public static Instructor signup(String chatId) {
        return Instructor.builder()
                .chatId(chatId)
                .interval(HOUR_1)
                .build();
    }

    public void updateInterval(IntervalType interval) {
        this.interval = interval;
    }
}
