package id.tomatech.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.tomatech.learn.entity.Member;
import id.tomatech.learn.repository.MemberRepository;

@RestController
@RequestMapping(value="/members")
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Member> registerMember(@Validated @RequestBody Member member, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);
		}
		
		Member persistedMembers = memberRepository.save(member);
		return new ResponseEntity<Member>(persistedMembers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Member> listMembers(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}
	
	@RequestMapping(value="/{memberId}", method=RequestMethod.GET)	
	public ResponseEntity<Member> detailMember(@PathVariable("memberId") Long id) {
		if(!memberRepository.exists(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Member member = memberRepository.findOne(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
}
