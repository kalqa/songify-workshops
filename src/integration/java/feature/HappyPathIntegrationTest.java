package feature;

import com.songify.SongifyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SongifyApplication.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class HappyPathIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    public MockMvc mockMvc;

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    }

    @Test
    public void happy_path() throws Exception {
        //  1. when I go to /songs then I can see no songs
        mockMvc.perform(get("/songs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.songs", empty()));
        //  2. when I post to /songs with Song "Till i collapse" then Song "Till i collapse" is returned with id 1
        mockMvc.perform(post("/songs")
                        .content("""
                                {
                                  "name": "Till i collapse",
                                  "releaseDate": "2024-03-15T13:55:21.850Z",
                                  "duration": 0,
                                  "language": "ENGLISH"
                                }
                                """.trim())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.id", is(1)))
                .andExpect(jsonPath("$.song.name", is("Till i collapse")))
                .andExpect(jsonPath("$.song.genre.id", is(1)))
                .andExpect(jsonPath("$.song.genre.name", is("default")));
        //  3. when I post to /songs with Song "Lose Yourself" then Song "Lose Yourself" is returned with id 2
        mockMvc.perform(post("/songs")
                        .content("""
                                {
                                  "name": "Lose Yourself",
                                  "releaseDate": "2024-03-15T13:55:21.850Z",
                                  "duration": 0,
                                  "language": "ENGLISH"
                                }
                                """.trim())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.id", is(2)))
                .andExpect(jsonPath("$.song.name", is("Lose Yourself")))
                .andExpect(jsonPath("$.song.genre.id", is(1)))
                .andExpect(jsonPath("$.song.genre.name", is("default")));
        //  4. when I go to /genres then I can see only default genre with id 1
        mockMvc.perform(get("/genres")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genres[0].id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("default")))
        ;
        //  5. when I post to /genres with Genre "Rap" then Genre "Rap" is returned with id 2
        mockMvc.perform(post("/genres")
                        .content("""
                                {
                                  "name": "Rap"
                                }
                                """.trim())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Rap")));
        //  6. when I go to /songs/1 then I can see default genre with id 1 and name default
        mockMvc.perform(get("/songs/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.genre.id", is(1)))
                .andExpect(jsonPath("$.song.name", is("Till i collapse")))
                .andExpect(jsonPath("$.song.id", is(1)))
                .andExpect(jsonPath("$.song.genre.name", is("default")));

        //  7. when I put to /songs/1/genres/1 then Genre with id 2 ("Rap") is added to Song with id 1 ("Til i collapse")
        mockMvc.perform(put("/songs/1/genres/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("updated")));

        //  8. when I go to /songs/1 then I can see "Rap" genre
        mockMvc.perform(get("/songs/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.genre.id", is(2)))
                .andExpect(jsonPath("$.song.genre.name", is("Rap")));
    }
}
