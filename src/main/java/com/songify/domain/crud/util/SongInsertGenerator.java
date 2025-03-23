package com.songify.domain.crud.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SongInsertGenerator {
    public static void main(String[] args) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BufferedWriter writer = new BufferedWriter(new FileWriter("insert_songs.sql"));

            // Generate 10,000 songs with random details and release dates
            Random random = new Random();
            Date baseDate = dateFormat.parse("2023-01-21 00:00:00");
            for (int i = 0; i < 10000; i++) {
                String releaseDate = dateFormat.format(new Date(baseDate.getTime() + i * 86400000L)); // Add a day
                String songName = "Song " + (random.nextInt(10000) + 1);
                int duration = 150 + random.nextInt(151);
                int albumId = random.nextInt(10) + 1;
                int genreId = random.nextInt(20) + 1;

                String sql = String.format("INSERT INTO song (name, duration, release_date, language, album_id, genre_id) " +
                                "VALUES ('%s', %d, '%s', 'ENGLISH', %d, %d);%n",
                        songName, duration, releaseDate, albumId, genreId);

                writer.write(sql);
            }

            writer.close();
            System.out.println("SQL statements generated successfully in insert_songs.sql");
        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        }
    }
}
