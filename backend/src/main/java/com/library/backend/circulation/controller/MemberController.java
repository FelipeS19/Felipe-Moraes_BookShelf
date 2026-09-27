package com.library.backend.circulation.controller;

import com.library.backend.circulation.domain.Member;
import com.library.backend.circulation.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.createMember(member), HttpStatus.CREATED);
    }
}
