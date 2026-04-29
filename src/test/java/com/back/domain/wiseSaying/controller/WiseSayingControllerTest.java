package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록")
    void t3() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("종료")
    void t4() {
        final String out = AppTestRunner.run("""
                종료
                """);

        assertThat(out)
                .contains("== 명언 앱 ==")
                .contains("명령)")
                .contains("프로그램이 종료합니다.");
    }

    @Test
    @DisplayName("등록 후 목록")
    void t5() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언 / 작성 / 수정")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("목록은 최신 명언부터 출력")
    void t6() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(out.indexOf("2 / 작자미상 / 과거에 집착하지 마라."))
                .isLessThan(out.indexOf("1 / 작자미상 / 현재를 사랑하라."));
    }

    @Test
    @DisplayName("삭제")
    void t7() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=1
                """);

        assertThat(out).contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("삭제 후 목록에서는 삭제된 명언 제외")
    void t8() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=1
                목록
                """);

        String listOut = out.substring(out.indexOf("번호 / 작가 / 명언 / 작성 / 수정"));

        assertThat(listOut)
                .contains("번호 / 작가 / 명언 / 작성 / 수정")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("존재하지 않는 명언 삭제")
    void t9() {
        final String out = AppTestRunner.run("""
                삭제?id=1
                """);

        assertThat(out).contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("삭제할 id가 없는 경우")
    void t10() {
        final String out = AppTestRunner.run("""
                삭제
                """);

        assertThat(out).contains("id를 숫자로 입력해주세요.");
    }

    @Test
    @DisplayName("삭제할 id가 숫자가 아닌 경우")
    void t11() {
        final String out = AppTestRunner.run("""
                삭제?id=abc
                """);

        assertThat(out).contains("id를 숫자로 입력해주세요.");
    }

    @Test
    @DisplayName("수정")
    void t12() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);

        assertThat(out)
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상")
                .contains("1 / 홍길동 / 현재와 자신을 사랑하라.");
    }

    @Test
    @DisplayName("수정해도 명언 번호는 유지")
    void t13() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);

        assertThat(out)
                .contains("1 / 홍길동 / 현재와 자신을 사랑하라.")
                .doesNotContain("2 / 홍길동 / 현재와 자신을 사랑하라.");
    }

    @Test
    @DisplayName("존재하지 않는 명언 수정")
    void t14() {
        final String out = AppTestRunner.run("""
                수정?id=1
                """);

        assertThat(out).contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정할 id가 없는 경우")
    void t15() {
        final String out = AppTestRunner.run("""
                수정
                """);

        assertThat(out).contains("id를 입력해주세요.");
    }

    @Test
    @DisplayName("수정할 id가 숫자가 아닌 경우")
    void t16() {
        final String out = AppTestRunner.run("""
                수정?id=abc
                """);

        assertThat(out).contains("id를 입력해주세요.");
    }

    @Test
    @DisplayName("비어있는 목록")
    void t17() {
        final String out = AppTestRunner.run("""
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언 / 작성 / 수정")
                .contains("-----------------------------")
                .doesNotContain(" / 작자미상 / ");
    }

    @Test
    @DisplayName("여러 개 등록 시 번호 증가")
    void t18() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                등록
                미래를 두려워하지 마라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .contains("3번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("삭제 후 새 명언 등록 시 번호는 증가")
    void t19() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=1
                등록
                다시 시작하라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("2번 명언이 등록되었습니다.")
                .contains("2 / 작자미상 / 다시 시작하라.")
                .doesNotContain("1 / 작자미상 / 다시 시작하라.");
    }

    @Test
    @DisplayName("수정 후 삭제")
    void t20() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                오늘을 사랑하라.
                이순신
                삭제?id=1
                목록
                """);

        String listOut = out.substring(out.indexOf("번호 / 작가 / 명언 / 작성 / 수정"));

        assertThat(out).contains("1번 명언이 삭제되었습니다.");
        assertThat(listOut).doesNotContain("1 / 이순신 / 오늘을 사랑하라.");
    }
}
