package com.luckvicky.blur.domain.channelboard.controller;

import com.luckvicky.blur.domain.board.model.entity.BoardType;
import com.luckvicky.blur.domain.channel.model.dto.response.ChannelListResponse;ChannelBoardController.java
import com.luckvicky.blur.domain.channelboard.model.dto.ChannelBoardDetailDto;
import com.luckvicky.blur.domain.channelboard.model.dto.ChannelBoardListDto;
import com.luckvicky.blur.domain.channelboard.model.dto.request.ChannelBoardCreateRequest;
import com.luckvicky.blur.domain.channelboard.model.dto.response.ChannelBoardDetailResponse;
import com.luckvicky.blur.domain.channelboard.model.dto.response.ChannelBoardListResponse;
import com.luckvicky.blur.domain.channelboard.service.ChannelBoardService;
import com.luckvicky.blur.global.jwt.model.ContextMember;
import com.luckvicky.blur.global.model.dto.Result;
import com.luckvicky.blur.global.security.AuthUser;
import com.luckvicky.blur.global.security.NullableAuthUser;
import com.luckvicky.blur.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Tag(name = "채널 게시글 API")
@RestController
@RequestMapping("/v1/channels/{channelId}/boards")
@RequiredArgsConstructor
public class ChannelBoardController {

    private final ChannelBoardService channelBoardService;

    @Operation(summary = "채널 게시글 생성 API")
    @Parameter(name = "channelId", description = "채널 고유 식별값", in = ParameterIn.PATH)
    @PostMapping
    public ResponseEntity<Result<ChannelBoardDetailDto>> createChannelBoard(
            @PathVariable(name = "channelId") UUID channelId,
            @Valid @RequestBody ChannelBoardCreateRequest request,
            @AuthUser ContextMember contextMember
            ) {
        ChannelBoardDetailDto createdBoard = channelBoardService.createChannelBoard(channelId, request,contextMember.getId(),
                BoardType.CHANNEL);
        return ResponseUtil.created(
                Result.<ChannelBoardDetailDto>builder()
                        .data(createdBoard)
                        .build()
        );
    }

    @Operation(
            summary = "채널 게시글 목록 검색 조회 API",
            description = "채널에 대한 게시물 목록을 검색한다."
    )
    @Parameters({
            @Parameter(name = "channelId", description = "채널 고유 식별값", in = ParameterIn.PATH),
            @Parameter(name = "pageNumber", description = "페이지 번호"),
            @Parameter(
                    name = "criteria",
                    description = "정렬 기준",
                    examples = {
                            @ExampleObject(name = "최신", value = "TIME"),
                            @ExampleObject(name = "좋아요", value = "LIKE"),
                            @ExampleObject(name = "조회수", value = "VIEW"),
                            @ExampleObject(name = "댓글", value = "COMMENT"),
                    }
            ),
    })
    @GetMapping
    public ResponseEntity<Result<ChannelBoardListResponse>> getChannelBoards(
            @PathVariable(name = "channelId")UUID channelId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(required = false, defaultValue = "0", value = "pageNumber") int pageNumber,
            @RequestParam(required = false, defaultValue = "TIME", value = "criteria") String criteria
    ){
        List<ChannelBoardListDto> channelBoardListDtos = channelBoardService.getChannelBoards(
                channelId,
                keyword,
                pageNumber,
                criteria
        );

        if (Objects.isNull(channelBoardListDtos) || channelBoardListDtos.isEmpty()) {
            return ResponseUtil.noContent(
                    Result.empty()
            );
        }

        return ResponseUtil.ok(
                Result.of(ChannelBoardListResponse.of(channelBoardListDtos))
        );
    }

    @Operation(
            summary = "채널 게시글 상세 조회 API",
            description = "특정 게시글에 대한 본문, 조회수를 조회한다. \n 댓글 조회는 '/v1/boards/{boardId}/comments' 활용"
    )
    @Parameter(name = "boardId", description = "게시글 고유 식별값", in = ParameterIn.PATH)
    @GetMapping("/{boardId}")
    public ResponseEntity<Result<ChannelBoardDetailResponse>> getBoardDetail(
            @PathVariable(name = "boardId") UUID boardId,
            @NullableAuthUser ContextMember nullableMember
    ) {

        ChannelBoardDetailDto boardDetail = channelBoardService.getBoardDetail(boardId, nullableMember);

        return ResponseUtil.ok(
                Result.of(ChannelBoardDetailResponse.of(boardDetail))
        );

    }

}
