package demo.kotlin.crud.controller

import demo.kotlin.crud.dto.BoardFormDto
import demo.kotlin.crud.service.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController // REST API
@RequestMapping("board")
class BoardController @Autowired constructor(val boardService: BoardService) {

    // 게시글 등록
    @PostMapping
    suspend fun addPost(@RequestBody boardFormDto: BoardFormDto): ResponseEntity<Any> {
        val save = boardService.save(boardFormDto)
        return ResponseEntity.ok().body(save)
    }

    //게시글 읽기
    @GetMapping("/{id}")
    suspend fun getPost(@PathVariable id: Long): ResponseEntity<Any> {
        val post = boardService.getPost(id)
        return ResponseEntity.ok().body(post)
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    suspend fun deletePost(@PathVariable id: Long): ResponseEntity<Any> {
        boardService.deletePost(id)
        return ResponseEntity.ok().body(true)
    }

    //게시글 수정
    @PutMapping("/{id}")
    suspend fun updatePost(
        @PathVariable id: Long,
        @RequestBody boardFormDto: BoardFormDto
    ): ResponseEntity<Any> {
        val post = boardService.updatePost(id, boardFormDto)
        return ResponseEntity.ok().body(post)
    }

    //게시글 목록
    @GetMapping("/list")
    suspend fun listPost(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(boardService.getPostList())
    }
}
