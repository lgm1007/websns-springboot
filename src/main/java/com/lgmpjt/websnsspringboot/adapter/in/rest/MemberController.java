package com.lgmpjt.websnsspringboot.adapter.in.rest;

import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberSearchUseCase searchUseCase;
	private final MemberCommandUseCase commandUseCase;

	@PostMapping
	@Operation(summary = "신규 유저 생성", description = "회원가입한 신규 유저를 생성해줍니다.")
	public ResponseEntity<Void> createNewMember(@RequestBody final MemberCreateDto memberCreateDto) {
		commandUseCase.createMember(memberCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{memberSeq}")
	@Operation(summary = "단일 유저 조회", description = "단일 유저 정보를 조회합니다.")
	public MemberDto searchOneMember(@PathVariable final Long memberSeq) {
		return searchUseCase.getMemberByMemberSeq(memberSeq);
	}

	@PutMapping("/{memberSeq}")
	@Operation(summary = "단일 유저 업데이트", description = "단일 유저 정보를 업데이트합니다.")
	public ResponseEntity<Long> updateOneMember(@PathVariable final Long memberSeq,
												@RequestBody final MemberDto memberDto) {
		commandUseCase.updateMember(memberDto);

		return ResponseEntity.ok(memberSeq);
	}

	@DeleteMapping("/{memberSeq}")
	@Operation(summary = "단일 유저 삭제", description = "단일 유저를 삭제합니다.")
	public ResponseEntity<Long> deleteOneMember(@PathVariable final Long memberSeq) {
		commandUseCase.deleteMember(memberSeq);

		return ResponseEntity.ok(memberSeq);
	}
}
