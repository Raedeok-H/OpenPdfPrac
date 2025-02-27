package dto;

import lombok.Data;

@Data
public class OverlayDTO {
    private Float x;
    private Float y;
    private Float width;
    private Float height;

    private String text;
}

/**
 * 아래처럼 좌하단부터 시작하는 퍼센트로 받을 것임
 * x가 0이면 좌측 끝, 100이면 우측 끝
 * y가 0이면 하단 끝, 100이면 상단 끝
 *
 * 아래가 나타내는 박스영역에서 x,y 좌표는 좌하단임
 * {
 *   "x": 58.87,
 *   "y": 94.44,
 *   "width": 40.52,
 *   "height": 4.28,
 *   "text": "홍길동"
 * }
 */