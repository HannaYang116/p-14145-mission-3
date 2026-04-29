package com.back;
//37강 레이어드 아키텍처부터
import java.util.Scanner;

import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.system.controller.SystemController;

public class App {
    private Scanner scanner = new Scanner(System.in);

    // 진입점 시작
    public void run() {
        System.out.println("== 명언 앱 ==");

        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController(scanner);

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getActionName()) {
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                case "목록" -> wiseSayingController.actionList();
                case "등록" -> wiseSayingController.actionWrite();
                case "삭제" -> wiseSayingController.actionDelete(rq);
                case "수정" ->wiseSayingController.actionModify(rq);
            }
        }

    }
    // 진입점 끝


}