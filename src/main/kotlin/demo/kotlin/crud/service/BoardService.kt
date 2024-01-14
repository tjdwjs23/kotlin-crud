package demo.kotlin.crud.service

import demo.kotlin.crud.dto.Board
import demo.kotlin.crud.dto.BoardFormDto
import demo.kotlin.crud.repository.BoardRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class BoardService @Autowired constructor(
    val boardRepository: BoardRepository,
    val modelMapper: ModelMapper
) {

    init {
        // ModelMapper 설정
        modelMapper.configuration.isSkipNullEnabled = true
    }

    @Transactional
    fun save(boardFormDto: BoardFormDto): Long {
        val board = modelMapper.map(boardFormDto, Board::class.java)
        val savedBoard = boardRepository.save(board)
        return savedBoard.id ?: throw IllegalStateException("Board ID should not be null after save.")
    }

    fun getPost(id: Long): Board? {
        return boardRepository.findById(id).orElse(null)
    }

    fun deletePost(id: Long) {
        boardRepository.deleteById(id)
    }

    @Transactional
    fun updatePost(id: Long, boardFormDto: BoardFormDto): Board {
        val post = boardRepository.findById(id).orElseThrow { EntityNotFoundException("Post not found with ID: $id") }
        post.updatePost(boardFormDto)
        return saveUpdatedPost(post)
    }

    private fun saveUpdatedPost(post: Board): Board {
        return boardRepository.save(post)
    }

    fun getPostList(): List<Board> {
        return boardRepository.findAll()
    }

}
