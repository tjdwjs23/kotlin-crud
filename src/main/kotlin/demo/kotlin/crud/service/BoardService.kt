package demo.kotlin.crud.service

import demo.kotlin.crud.dto.Board
import demo.kotlin.crud.dto.BoardFormDto
import demo.kotlin.crud.repository.BoardRepository
import kotlinx.coroutines.*
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
        modelMapper.configuration.isSkipNullEnabled = true
    }

    @Transactional
    suspend fun save(boardFormDto: BoardFormDto): Long = coroutineScope {
        val board = modelMapper.map(boardFormDto, Board::class.java)
        val savedBoard = async { boardRepository.save(board) }
        savedBoard.await().id ?: throw IllegalStateException("Board ID should not be null after save.")
    }

    suspend fun getPost(id: Long): Board? = coroutineScope {
        async { boardRepository.findById(id).orElse(null) }.await()
    }

    suspend fun deletePost(id: Long) {
        coroutineScope {
            async { boardRepository.deleteById(id) }.await()
        }
    }

    @Transactional
    suspend fun updatePost(id: Long, boardFormDto: BoardFormDto): Board = coroutineScope {
        val post = async { boardRepository.findById(id).orElseThrow { EntityNotFoundException("Post not found with ID: $id") } }.await()
        post.updatePost(boardFormDto)
        async { boardRepository.save(post) }.await()
    }

    suspend fun getPostList(): List<Board> = coroutineScope {
        async { boardRepository.findAll() }.await()
    }
}

