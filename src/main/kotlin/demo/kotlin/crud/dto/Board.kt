package demo.kotlin.crud.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var writer: String, //작성자
    var password: String, //암호
    var title: String, //글 제목
    var content: String //글 내용
){

    fun updatePost(boardFormDto: BoardFormDto){ //글 수정할 때
        writer = boardFormDto.writer
        title = boardFormDto.title
        password = boardFormDto.password
        content = boardFormDto.content
    }

}