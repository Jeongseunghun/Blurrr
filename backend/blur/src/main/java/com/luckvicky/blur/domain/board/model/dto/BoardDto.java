package com.luckvicky.blur.domain.board.model.dto;

import com.luckvicky.blur.domain.member.model.MemberDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Getter;

@Getter
@Schema(name = "게시물 정보")
public class BoardDto {

    @Schema(description = "고유 식별값")
    private UUID id;

    @Schema(description = "사용자 정보")
    private MemberDto member;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "생성 시간")
    private String createdAt;

    @Schema(description = "댓글 개수")
    private Long commentNumber;

    // :todo 좋아요 수

<<<<<<< HEAD
=======
    @Schema(description = "댓글 개수")
    private Long commentNumber;

>>>>>>> e103c411 (feat: 게시글 댓글 수 증가 기능 (#21))
}
