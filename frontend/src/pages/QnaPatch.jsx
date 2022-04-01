import React, { Component, useEffect } from 'react';
import styled from 'styled-components';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Button, Container } from 'react-bootstrap';
import { connect, useDispatch, useSelector } from 'react-redux';
import { Link, useParams,useNavigate } from 'react-router-dom';
import '../CSS/qnacreate.css';
import { useState } from 'react';
import { getAxios } from '../api.js';
import ReactHtmlParser from 'react-html-parser';
let 게시판이름 = styled.h1`
  text-align: center;
  margin-bottom: 5%;
`;

let 게시글제목 = styled.div`
  width: 100%;
  margin-bottom: 2%;
`;
let 글작성틀 = styled.div`
  width: 70%;
  margin-left: auto;
  margin-right: auto;
  padding-top: 5%;
`;
let 버튼위치 = styled.div`
  padding-top: 3%;
  text-align: center;
`;

function QnaPatch(props){
    let navigate = useNavigate();
    let state = useSelector((state) => state)
    let [제목, 제목값변경] = useState('');
    let [내용, 내용값변경] = useState('');
    let dispatch = useDispatch();
    const qnaId  = useParams().qnaId;
    const [qna, setQna] = useState({});

    const axios = getAxios();
    const getQna = () => {
        axios.get(`/api/qna/mine/${qnaId}`)
            .then(res => {
                제목값변경(res.data.body.success.title);
                내용값변경(res.data.body.success.content);
            })
            .catch(err => console.log(err))
    }
    const patchQna = () => {
        axios.patch(`/api/qna/mine/${qnaId}`, {
            title: 제목,
            content: 내용,
        });
        navigate(`/QnaDetail/${qnaId}`)

    }
    useEffect(()=> {
        getQna();
    
    }, []);

    return (
        <Container>
            <글작성틀>
                <게시판이름>
                    Q&A
                </게시판이름>
                <게시글제목>
                    <p>제목</p> 
                    {/* value={qna.title || ""} */}
                    
                    <input type="text" maxLength='50' style={ { width: "100%"}} value={제목 || ""} onChange={ (e) => {제목값변경(e.target.value)} }/>

                </게시글제목>
                <p>내용</p> 
                <CKEditor
                    editor={ ClassicEditor  }
                    data={ 내용 || ""}

                    onChange={ (event, editor) => {
                        const data = editor.getData();
                        
                        내용값변경(data)
                    } }

                />
                
                <버튼위치>
                <Link to = '/Qna'>
                    <Button variant="secondary" size="lg">취소</Button>
                </Link>
                {' '}
                <Button variant="primary" size="lg" onClick={(e) => {
                    patchQna();

                }}>등록</Button>

 
                </버튼위치>

            
      </글작성틀>
    </Container>
  );
}
export default QnaPatch;
// function state를props화(state){  //redux store 데이터 가져와서 props로 변환해주는 함수

//     return {
//         state: state

//     }
// }

// export default connect(state를props화)(QnaCreate)
