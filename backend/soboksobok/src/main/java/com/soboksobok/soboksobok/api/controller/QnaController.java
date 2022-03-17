package com.soboksobok.soboksobok.api.controller;

import com.soboksobok.soboksobok.api.entity.Qna;
import com.soboksobok.soboksobok.api.entity.dto.QnaDto;
import com.soboksobok.soboksobok.api.entity.user.User;
import com.soboksobok.soboksobok.api.service.QnaService;
import io.jsonwebtoken.lang.Collections;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qna")
public class QnaController {
    @Autowired
    QnaService service;

    @GetMapping
    @Operation(summary = "전체 qna 조회")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "전체 qna 조회 성공")
    })
    public ResponseEntity<List<QnaDto>> getAllQna() throws Exception{
        List<QnaDto> list=service.getAllQna();
        ResponseEntity<List<QnaDto>> res=new ResponseEntity(list, HttpStatus.OK);
        log.info("qna 개수 확인: "+list.size());
        return res;
    }

//    @Operation(summary = "내가 등록한 qna 전체 조회")
//    @ApiResponses( value = {
//            @ApiResponse(responseCode = "200", description = "내가 등록한 qna 전체 조회 성공")
//    })
//    public ResponseEntity<List<QnaDto>> getAllMyQna() throws Exception{
//        List<QnaDto> list=service.getAllMyQna();
//        ResponseEntity<List<QnaDto>> res=new ResponseEntity(list, HttpStatus.OK);
//        return res;
//    }

    @GetMapping("/{qna_id}")
    @Operation(summary = "qna 상세 조회")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "qna 상세 조회 성공")
    })
    public ResponseEntity<QnaDto> getQnaDetail(@PathVariable("qna_id") Long qna_id) throws Exception{
        QnaDto qna=service.getQnaDetail(qna_id);
        ResponseEntity<QnaDto> res=new ResponseEntity(qna, HttpStatus.OK);
        return res;
    }

    @GetMapping("/test")
    public String test() throws Exception{
        return "hi";
    }

}
