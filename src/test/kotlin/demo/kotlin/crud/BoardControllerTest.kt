package demo.kotlin.crud

import com.fasterxml.jackson.databind.ObjectMapper
import demo.kotlin.crud.controller.BoardController
import demo.kotlin.crud.dto.Board
import demo.kotlin.crud.dto.BoardFormDto
import demo.kotlin.crud.service.BoardService
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class BoardControllerTest {

    private val objectMapper = ObjectMapper()
    private val boardService: BoardService = mockk()
    private val boardController = BoardController(boardService)
    private val mockMvc: MockMvc = standaloneSetup(boardController).build()

    @Test
    fun `test addPost API`() {
        // Given
        val boardFormDto = BoardFormDto(
            writer = "John Doe",
            password = "password123",
            title = "Sample Title",
            content = "This is a sample content for testing."
        )

        every { boardService.save(match { it.writer == "John Doe" }) } returns 1L

        // When
        val result: ResultActions = mockMvc.perform(
            post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardFormDto))
        )

        // Then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$").value(1L))

    }

    @Test
    fun `test getPost API`() {
        // Given
        val postId = 1L
        val board = Board(
            id = postId,
            writer = "John Doe",
            password = "password123",
            title = "Sample Title",
            content = "This is a sample content for testing."
        )

        every { boardService.getPost(any()) } returns board

        // When
        val result: ResultActions = mockMvc.perform(get("/board/$postId"))

        // Then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(postId))
            .andExpect(jsonPath("$.writer").value("John Doe"))
            .andExpect(jsonPath("$.password").value("password123"))
            .andExpect(jsonPath("$.title").value("Sample Title"))
            .andExpect(jsonPath("$.content").value("This is a sample content for testing."))

    }

    @Test
    fun `test deletePost API`() {
        // Given
        val idToDelete = 1L
        every { boardService.deletePost(idToDelete) } just Runs

        // When
        val result: ResultActions = mockMvc.perform(delete("/board/$idToDelete"))

        // Then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$").value(true))

        verify { boardService.deletePost(idToDelete) }
    }

    @Test
    fun `test updatePost API`() {
        // Given
        val idToUpdate = 1L
        val boardFormDto = BoardFormDto(
            writer = "Updated Writer",
            password = "Updated Password",
            title = "Updated Title",
            content = "Updated Content"
        )
        every { boardService.updatePost(idToUpdate, any()) } returns Board(
            id = idToUpdate,
            writer = boardFormDto.writer,
            password = boardFormDto.password,
            title = boardFormDto.title,
            content = boardFormDto.content
        )

        // When
        val result: ResultActions = mockMvc.perform(
            put("/board/$idToUpdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardFormDto))
        )

        // Then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(idToUpdate))
            .andExpect(jsonPath("$.writer").value(boardFormDto.writer))
            .andExpect(jsonPath("$.password").value(boardFormDto.password))
            .andExpect(jsonPath("$.title").value(boardFormDto.title))
            .andExpect(jsonPath("$.content").value(boardFormDto.content))

        verify { boardService.updatePost(idToUpdate, boardFormDto) }
    }

    @Test
    fun `test listPost API`() {
        // Given
        val boardList = listOf(
            Board(1L, "Writer1", "Password1", "Title1", "Content1"),
            Board(2L, "Writer2", "Password2", "Title2", "Content2"),
            Board(3L, "Writer3", "Password3", "Title3", "Content3")
        )
        every { boardService.getPostList() } returns boardList

        // When
        val result: ResultActions = mockMvc.perform(get("/board/list"))

        // Then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(boardList[0].id))
            .andExpect(jsonPath("$[0].writer").value(boardList[0].writer))
            .andExpect(jsonPath("$[0].password").value(boardList[0].password))
            .andExpect(jsonPath("$[0].title").value(boardList[0].title))
            .andExpect(jsonPath("$[0].content").value(boardList[0].content))
            .andExpect(jsonPath("$[1].id").value(boardList[1].id))
            .andExpect(jsonPath("$[1].writer").value(boardList[1].writer))
            .andExpect(jsonPath("$[1].password").value(boardList[1].password))
            .andExpect(jsonPath("$[1].title").value(boardList[1].title))
            .andExpect(jsonPath("$[1].content").value(boardList[1].content))
            .andExpect(jsonPath("$[2].id").value(boardList[2].id))
            .andExpect(jsonPath("$[2].writer").value(boardList[2].writer))
            .andExpect(jsonPath("$[2].password").value(boardList[2].password))
            .andExpect(jsonPath("$[2].title").value(boardList[2].title))
            .andExpect(jsonPath("$[2].content").value(boardList[2].content))

        verify { boardService.getPostList() }
    }


}
